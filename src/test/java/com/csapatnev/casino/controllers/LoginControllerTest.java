package com.csapatnev.casino.controllers;

import com.csapatnev.casino.services.UserService;
import com.csapatnev.casino.testable.TestableLoginController;
import com.csapatnev.casino.utils.AdminInserter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

public class LoginControllerTest {

    private UserService mockedUserService;
    private AdminInserter mockedAdminInserter;
    private TestableLoginController loginController;

    @BeforeEach
    public void setUp() {
        // Mock the UserService and AdminInserter dependencies
        mockedUserService = mock(UserService.class);
        mockedAdminInserter = mock(AdminInserter.class);

        // Instantiate controller with mocked dependencies
        loginController = new TestableLoginController(mockedUserService, mockedAdminInserter);
    }

    @Test
    public void testAdminLogin() throws IOException {
        // Set the credentials to simulate admin login
        loginController.setEmail("admin@admin");
        loginController.setPassword("admin");

        // Mock userService.authenticate to return true for admin login
        when(mockedUserService.authenticate("admin@admin", "admin")).thenReturn(true);

        // Test login
        assertDoesNotThrow(() -> loginController.login());

        // Verify the expected interaction and behavior
        verify(mockedUserService, times(1)).authenticate("admin@admin", "admin");
    }

    @Test
    public void testRegularUserLogin() throws IOException {
        // Set credentials for a valid user
        loginController.setEmail("user@domain.com");
        loginController.setPassword("password");

        // Mock userService.authenticate to return true
        when(mockedUserService.authenticate("user@domain.com", "password")).thenReturn(true);

        // Test login
        assertDoesNotThrow(() -> loginController.login());

        // Verify userService.authenticate is invoked
        verify(mockedUserService, times(1)).authenticate("user@domain.com", "password");
    }

    @Test
    public void testInvalidLogin() throws IOException {
        // Set invalid credentials
        loginController.setEmail("wronguser");
        loginController.setPassword("wrongpassword");

        // Mock userService.authenticate to return false
        when(mockedUserService.authenticate("wronguser", "wrongpassword")).thenReturn(false);

        // Test login
        assertDoesNotThrow(() -> loginController.login());

        // Verify userService.authenticate is invoked
        verify(mockedUserService, times(1)).authenticate("wronguser", "wrongpassword");
    }
}
