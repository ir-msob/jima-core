package ir.msob.jima.core.beans.nativesupport;

import ir.msob.jima.core.commons.profile.ProfileConstants;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportRuntimeHints;
import org.springframework.context.annotation.Profile;

/**
 * This class is a configuration class that enables native support for core commons functionality in the application.
 * It is marked with the `@Configuration` annotation to indicate that it is a source of bean definitions.
 * The `@Profile` annotation specifies that this class should be active when the 'ProfileConstants.NATIVE' profile is active.
 * The `@ImportRuntimeHints` annotation is used to import the 'CoreCommonsRuntimeHints' class, which may provide runtime hints and configuration for core commons functionality.
 */
@Profile(ProfileConstants.NATIVE)
@ImportRuntimeHints(CoreCommonsRuntimeHints.class)
@Configuration
public class CoreCommonsNativeSupport {
}