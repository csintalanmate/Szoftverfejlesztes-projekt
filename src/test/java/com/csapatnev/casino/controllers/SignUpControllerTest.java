package com.csapatnev.casino.controllers;

import com.csapatnev.casino.models.User;
import com.csapatnev.casino.services.UserService;
import com.csapatnev.casino.testable.TestableSignUpController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class SignUpControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private TestableSignUpController signUpController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        signUpController = new TestableSignUpController(userService);
    }

    @Test
    void testSaveUserHelp_ValidInput() {
        // Simulate valid user input
        signUpController.setFirstName("John");
        signUpController.setLastName("Doe");
        signUpController.setDob(LocalDate.of(1990, 1, 1));
        signUpController.setEmail("john.doe@example.com");
        signUpController.setPassword("securePassword123");
        signUpController.setMale(true);

        // Trigger save logic
        signUpController.saveUserHelp();

        // Verify userService.save is called once with expected parameters
        verify(userService, times(1)).save(argThat(user ->
                user.getFirstName().equals("John") &&
                        user.getLastName().equals("Doe") &&
                        user.getDob().equals(LocalDate.of(1990, 1, 1)) &&
                        user.getEmail().equals("john.doe@example.com") &&
                        user.getPassword().equals("securePassword123") &&
                        user.getGender().equals("Male")
        ));
    }

    @Test
    void testSaveUserHelp_InvalidEmail() {
        // Simulate invalid email input
        signUpController.setFirstName("Jane");
        signUpController.setLastName("Doe");
        signUpController.setDob(LocalDate.of(1992, 2, 2));
        signUpController.setEmail("invalidemail");
        signUpController.setPassword("password123");
        signUpController.setMale(false);

        // Trigger save logic
        signUpController.saveUserHelp();

        // Verify userService.save is not called
        verify(userService, times(0)).save(any());
    }

    @Test
    void testSaveUserHelp_MissingFields() {
        // Simulate missing fields
        signUpController.setFirstName("");
        signUpController.setLastName("");
        signUpController.setDob(null);
        signUpController.setEmail("");
        signUpController.setPassword("");
        signUpController.setMale(false);

        // Trigger save logic
        signUpController.saveUserHelp();

        // Verify userService.save is not called
        verify(userService, times(0)).save(any());
    }
}
