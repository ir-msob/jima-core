package ir.msob.jima.core.beans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.boot.logging.DeferredLog;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Improved EnvironmentPostProcessor:
 * - loads classpath resources matching a configurable pattern (default: jima-config-*)
 * - supports .yml, .yaml and .properties
 * - loads profile-specific variants (e.g. jima-config-xyz-dev.yaml) for active profiles
 * - sorts resources deterministically before registering
 * - registers as last (lowest precedence) so application/env/cli can override
 */
public class ApplicationPatternEnvironmentPostProcessor implements EnvironmentPostProcessor, Ordered {

    private final DeferredLog log = new DeferredLog();

    // default pattern (without extension)
    private static final String DEFAULT_BASENAME_PATTERN = "classpath*:/META-INF/spring/jima-config-*";

    private static final String[] EXTENSIONS = {".yml", ".yaml", ".properties"};

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        // allow override from system property or existing environment property (if present)
        String basePattern = getConfiguredBasePattern(environment);

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        YamlPropertySourceLoader yamlLoader = new YamlPropertySourceLoader();
        PropertiesPropertySourceLoader propertiesLoader = new PropertiesPropertySourceLoader();

        try {
            List<Resource> allResources = new ArrayList<>();

            // load non-profile resources
            for (String ext : EXTENSIONS) {
                Resource[] resources = resolver.getResources(basePattern + ext);
                allResources.addAll(Arrays.asList(resources));
            }

            // also attempt to load profile-specific resources for active profiles
            String[] activeProfiles = environment.getActiveProfiles();
            if (activeProfiles != null && activeProfiles.length > 0) {
                for (String profile : activeProfiles) {
                    for (String ext : EXTENSIONS) {
                        Resource[] resources = resolver.getResources(basePattern + "-" + profile + ext);
                        allResources.addAll(Arrays.asList(resources));
                    }
                }
            }

            // filter existing and sort deterministically by filename+description
            List<Resource> existing = allResources.stream()
                    .filter(Objects::nonNull)
                    .filter(Resource::exists)
                    .distinct()
                    .sorted(Comparator.comparing(r -> safeResourceName(r)))
                    .collect(Collectors.toList());

            if (existing.isEmpty()) {
                log.debug("No jima-config resources found for pattern: " + basePattern);
                return;
            }

            for (Resource resource : existing) {
                try {
                    String filename = safeResourceName(resource);
                    if (filename.endsWith(".yml") || filename.endsWith(".yaml")) {
                        List<PropertySource<?>> sources = yamlLoader.load("jima:" + filename, resource);
                        registerSources(environment, sources, resource);
                    } else if (filename.endsWith(".properties")) {
                        List<PropertySource<?>> sources = propertiesLoader.load("jima:" + filename, resource);
                        registerSources(environment, sources, resource);
                    } else {
                        log.warn("Unknown extension, skipping resource: " + resource.getDescription());
                    }
                } catch (Exception ex) {
                    log.warn("Failed to load resource: " + resource.getDescription(), ex);
                }
            }
        } catch (IOException e) {
            log.warn("Failed to resolve resources for pattern", e);
        } finally {
            // flush deferred logs into real logger
            log.replayTo(ApplicationPatternEnvironmentPostProcessor.class);
        }
    }

    private void registerSources(ConfigurableEnvironment environment, List<PropertySource<?>> sources, Resource resource) {
        if (sources == null || sources.isEmpty()) return;
        for (PropertySource<?> src : sources) {
            // addLast -> lowest precedence (application and env override)
            environment.getPropertySources().addLast(src);
            log.info("Loaded jima config: " + resource.getDescription() + " -> propertySource=" + src.getName());
        }
    }

    private String getConfiguredBasePattern(ConfigurableEnvironment env) {
        // 1) system property, 2) environment property, 3) default
        String sys = System.getProperty("jima.config.pattern");
        if (sys != null && !sys.isBlank()) return sys;
        try {
            String envProp = env.getProperty("jima.config.pattern");
            if (envProp != null && !envProp.isBlank()) return envProp;
        } catch (Exception ignored) {
            // environment might not yet contain all props — ignore safely
        }
        return DEFAULT_BASENAME_PATTERN;
    }

    private static String safeResourceName(Resource r) {
        try {
            String fname = r.getFilename();
            if (fname != null) return fname;
            // fallback to description
            return r.getDescription() != null ? r.getDescription() : r.toString();
        } catch (Exception e) {
            return r.toString();
        }
    }

    @Override
    public int getOrder() {
        // low precedence so application-level config and env vars can override
        return Ordered.LOWEST_PRECEDENCE;
    }
}
