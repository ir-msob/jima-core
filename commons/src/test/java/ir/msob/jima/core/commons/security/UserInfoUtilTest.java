package ir.msob.jima.core.commons.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.io.JsonEOFException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.msob.jima.core.commons.ConcreteBaseUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class UserInfoUtilTest {

    private ObjectMapper objectMapper;
    private ConcreteBaseUser testUser;

    @BeforeEach
    void setup() {
        objectMapper = new ObjectMapper();
        objectMapper.registerSubtypes(ConcreteBaseUser.class);
        testUser = new ConcreteBaseUser("ID", "SESSION_ID", "NAME", "USERNAME", new TreeSet<>(List.of("ROLE")), "WEB");
    }

    @Disabled // TODO
    @Test
    void testEncodeUserWithUserPresent() throws JsonProcessingException {
        String encodedUser = UserInfoUtil.encodeUser(objectMapper, testUser);

        assertNotNull(encodedUser);
        assertFalse(encodedUser.isEmpty());

        // Decode the encoded user and check if it matches the original user.
        ConcreteBaseUser decodedUser = UserInfoUtil.decodeUser(objectMapper, encodedUser, ConcreteBaseUser.class);
        assertNotNull(decodedUser);
        assertEquals(testUser, decodedUser);
    }

    @Test
    void testEncodeUserWithUserNotPresent() throws JsonProcessingException {
        String encodedUser = UserInfoUtil.encodeUser(objectMapper, testUser);

        assertNotNull(encodedUser);
        assertFalse(encodedUser.isEmpty());
    }

    @Disabled // TODO
    @Test
    void testDecodeUserWithValidEncodedUser() throws JsonProcessingException {
        String encodedUser = UserInfoUtil.encodeUser(objectMapper, testUser);
        ConcreteBaseUser decodedUser = UserInfoUtil.decodeUser(objectMapper, encodedUser, ConcreteBaseUser.class);

        assertNotNull(decodedUser);
        assertEquals(testUser, decodedUser);
    }

    @Test
    void testDecodeUserWithEmptyEncodedUser() {
        String encodedUser = ""; // Empty encoded user
        assertThrows(IllegalArgumentException.class, () -> {
            UserInfoUtil.decodeUser(objectMapper, encodedUser, ConcreteBaseUser.class);
        });
    }

    @Test
    void testDecodeUserWithInvalidEncodedUser() {
        String encodedUser = "InvalidBase64String"; // Invalid encoded user
        assertThrows(JsonEOFException.class, () -> UserInfoUtil.decodeUser(objectMapper, encodedUser, ConcreteBaseUser.class));
    }

}
