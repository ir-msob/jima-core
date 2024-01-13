package ir.msob.jima.core.commons.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.io.JsonEOFException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.msob.jima.core.commons.ConcreteBaseUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class UserInfoUtilTest {

    private ObjectMapper objectMapper;
    private ConcreteBaseUser testUser;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerSubtypes(ConcreteBaseUser.class);
        testUser = new ConcreteBaseUser("ID", "SESSION_ID", "USERNAME", new TreeSet<>(List.of("ROLE")), "WEB");
    }

    @Disabled // TODO
    @Test
    void testEncodeUserWithUserPresent() throws JsonProcessingException {
        Optional<ConcreteBaseUser> userOptional = Optional.of(testUser);
        String encodedUser = UserInfoUtil.encodeUser(objectMapper, userOptional);

        assertNotNull(encodedUser);
        assertFalse(encodedUser.isEmpty());

        // Decode the encoded user and check if it matches the original user.
        Optional<ConcreteBaseUser> decodedUser = UserInfoUtil.decodeUser(objectMapper, encodedUser, ConcreteBaseUser.class);
        assertTrue(decodedUser.isPresent());
        assertEquals(testUser, decodedUser.get());
    }

    @Test
    void testEncodeUserWithUserNotPresent() throws JsonProcessingException {
        Optional<ConcreteBaseUser> userOptional = Optional.empty();
        String encodedUser = UserInfoUtil.encodeUser(objectMapper, userOptional);

        assertNotNull(encodedUser);
        assertTrue(encodedUser.isEmpty());
    }

    @Disabled // TODO
    @Test
    void testDecodeUserWithValidEncodedUser() throws JsonProcessingException {
        String encodedUser = UserInfoUtil.encodeUser(objectMapper, Optional.of(testUser));
        Optional<ConcreteBaseUser> decodedUser = UserInfoUtil.decodeUser(objectMapper, encodedUser, ConcreteBaseUser.class);

        assertTrue(decodedUser.isPresent());
        assertEquals(testUser, decodedUser.get());
    }

    @Test
    void testDecodeUserWithEmptyEncodedUser() throws JsonProcessingException {
        String encodedUser = ""; // Empty encoded user
        Optional<ConcreteBaseUser> decodedUser = UserInfoUtil.decodeUser(objectMapper, encodedUser, ConcreteBaseUser.class);

        assertTrue(decodedUser.isEmpty());
    }

    @Test
    void testDecodeUserWithInvalidEncodedUser() throws JsonProcessingException {
        String encodedUser = "InvalidBase64String"; // Invalid encoded user
        assertThrows(JsonEOFException.class, () -> UserInfoUtil.decodeUser(objectMapper, encodedUser, ConcreteBaseUser.class));
    }

}
