package ir.msob.jima.core.beans.profile;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * Spring profile service for checking active profiles.
 * <p>
 * This service provides a method to check if a specific profile is active in the Spring application context.
 *
 * @author Yaqub Abdi
 */
@Service
public class ProfileService {
    private final Environment environment;

    /**
     * Constructs a ProfileService with the provided Spring Environment.
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
        for (String activeProfile : environment.getActiveProfiles()) {
            if (activeProfile.equals(profile)) {
                return true;
            }
        }
        return false;
    }
}
