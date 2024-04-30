package test;

import group.chatting.application.UserAuthentication;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserAuthenticationTest {

    @Test
    public void testAuthenticate_ValidCredentials() {
        // Arrange
        String username = "user1";
        String password = "password1";

        // Act
        boolean isAuthenticated = UserAuthentication.authenticate(username, password);

        // Assert
        assertTrue(isAuthenticated);
    }

    @Test
    public void testAuthenticate_InvalidCredentials() {
        // Arrange
        String username = "invalidUser";
        String password = "wrongPassword";

        // Act
        boolean isAuthenticated = UserAuthentication.authenticate(username, password);

        // Assert
        assertFalse(isAuthenticated);
    }
}