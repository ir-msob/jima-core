package ir.msob.jima.core.beans;

import ir.msob.jima.core.commons.Constants;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * The 'MsobAutoConfiguration' class is a Spring configuration class that is used to enable component scanning within the 'ir.msob.jima' base package. This class is often part of a library or framework that provides default configurations for Spring-based applications.
 * <p>
 * It is annotated with '@Configuration', indicating that it defines configuration beans for the Spring application context.
 * <p>
 * The '@ComponentScan' annotation is used to specify the base packages for component scanning. In this case, it includes all components (classes annotated with '@Component', '@Service', '@Repository', etc.) under the 'ir.msob.jima' package and its sub-packages.
 * <p>
 * By importing this configuration class into your Spring application, you enable the automatic detection and registration of Spring components within the specified package, which can simplify the configuration process and reduce the need for explicit bean definitions.
 */
@Configuration
@ComponentScan(basePackages = Constants.FRAMEWORK_PACKAGE_PREFIX)
public class MsobAutoConfiguration {
}