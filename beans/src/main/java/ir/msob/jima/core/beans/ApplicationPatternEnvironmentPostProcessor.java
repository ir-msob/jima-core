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

/**
 * Improved EnvironmentPostProcessor:
 * - loads classpath resources matching a configurable pattern (default: jima-config-*)
 * - supports .yml, .yaml and .properties
 * - loads profile-specific variants (e.g. jima-config-xyz-dev.yaml) for active profiles
 * - sorts resources deterministically before registering
 * - registers as last (lowest precedence) so application/env/cli can override
 */
public class ApplicationPatternEnvironmentPostProcessor implements EnvironmentPostProcessor, Ordered {

    private static final String DEFAULT_BASENAME_PATTERN = "classpath*:/META-INF/spring/jima-config-*";
    private static final String[] EXTENSIONS = {".yml", ".yaml", ".properties"};
    private static final String CONFIG_PATTERN_PROPERTY = "jima.config.pattern";

    private final DeferredLog log = new DeferredLog();

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        try {
            processConfigurationResources(environment);
        } finally {
            log.replayTo(ApplicationPatternEnvironmentPostProcessor.class);
        }
    }

    private void processConfigurationResources(ConfigurableEnvironment environment) {
        String basePattern = getConfiguredBasePattern(environment);
        List<Resource> resources = findAllConfigurationResources(basePattern, environment);

        if (resources.isEmpty()) {
            log.debug("No jima-config resources found for pattern: " + basePattern);
            return;
        }

        loadAndRegisterPropertySources(environment, resources);
    }

    private List<Resource> findAllConfigurationResources(String basePattern, ConfigurableEnvironment environment) {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        List<Resource> allResources = new ArrayList<>();

        try {
            // Load non-profile specific resources
            loadResourcesByExtensions(resolver, basePattern, allResources);

            // Load profile-specific resources for active profiles
            loadProfileSpecificResources(resolver, basePattern, environment.getActiveProfiles(), allResources);

        } catch (IOException e) {
            log.warn("Failed to resolve resources for pattern: " + basePattern, e);
        }

        return filterAndSortResources(allResources);
    }

    private void loadResourcesByExtensions(PathMatchingResourcePatternResolver resolver,
                                           String basePattern,
                                           List<Resource> targetList) throws IOException {
        for (String extension : EXTENSIONS) {
            Resource[] resources = resolver.getResources(basePattern + extension);
            targetList.addAll(Arrays.asList(resources));
        }
    }

    private void loadProfileSpecificResources(PathMatchingResourcePatternResolver resolver,
                                              String basePattern,
                                              String[] activeProfiles,
                                              List<Resource> targetList) throws IOException {
        for (String profile : activeProfiles) {
            for (String extension : EXTENSIONS) {
                Resource[] resources = resolver.getResources(basePattern + "-" + profile + extension);
                targetList.addAll(Arrays.asList(resources));
            }
        }
    }

    private List<Resource> filterAndSortResources(List<Resource> resources) {
        return resources.stream()
                .filter(Objects::nonNull)
                .filter(Resource::exists)
                .distinct()
                .sorted(Comparator.comparing(this::safeResourceName))
                .toList();
    }

    private void loadAndRegisterPropertySources(ConfigurableEnvironment environment, List<Resource> resources) {
        YamlPropertySourceLoader yamlLoader = new YamlPropertySourceLoader();
        PropertiesPropertySourceLoader propertiesLoader = new PropertiesPropertySourceLoader();

        for (Resource resource : resources) {
            loadAndRegisterPropertySource(environment, resource, yamlLoader, propertiesLoader);
        }
    }

    private void loadAndRegisterPropertySource(ConfigurableEnvironment environment,
                                               Resource resource,
                                               YamlPropertySourceLoader yamlLoader,
                                               PropertiesPropertySourceLoader propertiesLoader) {
        try {
            String filename = safeResourceName(resource);
            List<PropertySource<?>> propertySources = loadPropertySources(resource, filename, yamlLoader, propertiesLoader);
            registerPropertySources(environment, propertySources, resource);

        } catch (Exception ex) {
            log.warn("Failed to load resource: " + resource.getDescription(), ex);
        }
    }

    private List<PropertySource<?>> loadPropertySources(Resource resource,
                                                        String filename,
                                                        YamlPropertySourceLoader yamlLoader,
                                                        PropertiesPropertySourceLoader propertiesLoader) throws IOException {
        if (filename.endsWith(".yml") || filename.endsWith(".yaml")) {
            return yamlLoader.load("jima:" + filename, resource);
        } else if (filename.endsWith(".properties")) {
            return propertiesLoader.load("jima:" + filename, resource);
        } else {
            log.warn("Unknown extension, skipping resource: " + resource.getDescription());
            return Collections.emptyList();
        }
    }

    private void registerPropertySources(ConfigurableEnvironment environment,
                                         List<PropertySource<?>> propertySources,
                                         Resource resource) {
        if (propertySources == null || propertySources.isEmpty()) {
            return;
        }

        for (PropertySource<?> propertySource : propertySources) {
            environment.getPropertySources().addLast(propertySource);
            log.info("Loaded jima config: " + resource.getDescription() + " -> propertySource=" + propertySource.getName());
        }
    }

    private String safeResourceName(Resource resource) {
        try {
            String filename = resource.getFilename();
            return filename != null ? filename : resource.getDescription();
        } catch (Exception e) {
            return resource.toString();
        }
    }

    private String getConfiguredBasePattern(ConfigurableEnvironment environment) {
        // Try system property first
        String systemPattern = System.getProperty(CONFIG_PATTERN_PROPERTY);
        if (isValidPattern(systemPattern)) {
            return systemPattern;
        }

        // Try environment property
        try {
            String envPattern = environment.getProperty(CONFIG_PATTERN_PROPERTY);
            if (isValidPattern(envPattern)) {
                return envPattern;
            }
        } catch (Exception ignored) {
            // Environment might not yet contain all properties — ignore safely
        }

        // Return default pattern
        return DEFAULT_BASENAME_PATTERN;
    }

    private boolean isValidPattern(String pattern) {
        return pattern != null && !pattern.isBlank();
    }

    @Override
    public int getOrder() {
        // Low precedence so application-level config and env vars can override
        return Ordered.LOWEST_PRECEDENCE;
    }
}