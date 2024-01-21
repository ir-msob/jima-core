package ir.msob.jima.core.beans.configuration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The `SecurityProperties` class is a simple POJO (Plain Old Java Object) that holds security-related properties.
 * It uses Lombok annotations for automatic generation of getters, setters, a no-argument constructor, and a toString method.
 * The `defaultClientRegistrationId` field is used to store the default client registration ID for the security configuration.
 */
@Setter
@Getter
@NoArgsConstructor
@ToString
public class SecurityProperties {
    private String defaultClientRegistrationId = "service-client";
}
