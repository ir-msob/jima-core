package ir.msob.jima.core.beans.nativesupport;

import ir.msob.jima.core.commons.profile.ProfileConstants;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportRuntimeHints;
import org.springframework.context.annotation.Profile;

/**
 * This Java class is part of the 'ir.msob.jima.core.beans.nativesupport' package and serves as a configuration class that enables native support for core commons functionality in the application.
 * It is annotated with the '@Configuration' annotation, indicating that it is a Spring configuration class.
 * <p>
 * The '@Profile' annotation specifies that this class should be active when the 'ProfileConstants.NATIVE' profile is active in the Spring application context.
 * <p>
 * The '@ImportRuntimeHints' annotation is used to import the 'CoreCommonsRuntimeHints' class, which may provide runtime hints and configuration for core commons functionality.
 * <p>
 * Overall, this class is responsible for configuring native support for core commons functionality in the application when the 'ProfileConstants.NATIVE' profile is active.
 */
@Profile(ProfileConstants.NATIVE)
@ImportRuntimeHints(CoreCommonsRuntimeHints.class)
@Configuration
public class CoreCommonsNativeSupport {
}
