package ir.msob.jima.core.beans.profile;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * This service class provides functionality to check if a specific profile is active in the Spring application context.
 * It is marked as a Spring service to be detected during classpath scanning.
 * The active profiles are retrieved from the Spring Environment.
 */
@Service
public class ProfileService {
    // The Spring Environment to be used for profile checking
    private final Environment environment;

    /**
     * Constructs a new ProfileService with the provided Spring Environment.
     *
     * @param environment The Spring Environment to be used for profile checking.
     */
    public ProfileService(Environment environment) {
        super();
        this.environment = environment;
    }

    /**
     * Check if a specific profile is active in the application context.
     *
     * @param profile The name of the profile to check.
     * @return `true` if the profile is active, `false` otherwise.
     */
    public Boolean isActiveProfile(String profile) {
        // Iterate over the active profiles
        for (String activeProfile : environment.getActiveProfiles()) {
            // If the active profile matches the provided profile, return true
            if (activeProfile.equals(profile)) {
                return true;
            }
        }
        // If no match is found, return false
        return false;
    }
}