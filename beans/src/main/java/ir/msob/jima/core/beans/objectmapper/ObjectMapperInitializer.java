package ir.msob.jima.core.beans.objectmapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ir.msob.jima.core.commons.Constants;
import ir.msob.jima.core.commons.childdomain.BaseChildDto;
import ir.msob.jima.core.commons.domain.BaseDto;
import ir.msob.jima.core.commons.methodstats.MethodStats;
import ir.msob.jima.core.commons.shared.BaseType;
import ir.msob.jima.core.commons.shared.ModelType;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * The 'ObjectMapperInitializer' class is a component responsible for initializing an instance of the Jackson ObjectMapper with custom settings in a Spring-based application.
 * It provides configuration for registering subtypes and modules for better JSON serialization and deserialization.
 * <p>
 * The '@Component' annotation indicates that this class is a Spring component and should be automatically managed by the Spring container.
 * <p>
 * The 'ObjectMapper' instance is injected into this component via constructor injection.
 */
@Component
@RequiredArgsConstructor
public class ObjectMapperInitializer {

    private final ObjectMapper objectMapper;

    /**
     * Initialize the ObjectMapper by registering subtypes and modules. This method is executed after the object is constructed (using '@PostConstruct').
     * It ensures that the ObjectMapper is properly configured with subtypes and modules to handle custom serialization and deserialization.
     */
    @MethodStats
    @PostConstruct
    public void initializeObjectMapper() {
        registerSubTypes();
        registerModules();
    }

    /**
     * Register subtypes for various DTO and model classes. This method scans and registers subtypes of 'BaseType', 'BaseChildDto', and 'ModelType' within specific package prefixes.
     */

    private void registerSubTypes() {
        Set<String> prefixes = new HashSet<>();
        prefixes.add(Constants.CORE_FRAMEWORK_PACKAGE_PREFIX);
        prefixes.forEach(prefix -> {
            Reflections reflections = new Reflections(prefix);

            reflections.getSubTypesOf(BaseType.class)
                    .forEach(objectMapper::registerSubtypes);

            reflections.getSubTypesOf(BaseDto.class)
                    .stream()
                    .map(clazz -> (Class<? extends BaseDto<?>>) clazz)
                    .forEach(objectMapper::registerSubtypes);


            reflections.getSubTypesOf(BaseChildDto.class)
                    .stream()
                    .map(clazz -> (Class<? extends BaseChildDto<?>>) clazz)
                    .forEach(objectMapper::registerSubtypes);

            reflections.getSubTypesOf(ModelType.class)
                    .forEach(objectMapper::registerSubtypes);
        });
    }

    /**
     * Register custom Jackson modules, such as the Jdk8Module. This method adds custom modules to the ObjectMapper to handle specific data types or serialization requirements.
     */
    private void registerModules() {
        objectMapper.registerModule(new Jdk8Module());
        objectMapper.registerModule(new JavaTimeModule());

    }
}
