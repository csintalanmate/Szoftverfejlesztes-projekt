package com.csapatnev.casino.services;

import com.csapatnev.casino.models.User;
import com.csapatnev.casino.repositories.UserRepository;
import com.csapatnev.casino.services.implementations.UserServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserRepository userRepository;
    private UserServiceImplementation userService;

    @BeforeEach
    public void setUp() {
        // Initialize mock objects manually
        userRepository = mock(UserRepository.class);
        userService = new UserServiceImplementation();
        userService.setUserRepository(userRepository); // Inject mock into the service
    }

    @Test
    public void testSaveUser() {
        // Arrange
        User user = new User(1L, "John", "Doe", "john.doe@example.com", "password123", null, "Male", "Admin");
        when(userRepository.save(user)).thenReturn(user);

        // Act
        User savedUser = userService.save(user);

        // Assert
        verify(userRepository).save(user); // Verify repository's save is called
        assertTrue(savedUser.getId().equals(1L));
    }

    @Test
    public void testUpdateUser() {
        User user = new User(2L, "Jane", "Doe", "jane.doe@example.com", "password123", null, "Female", "User");
        when(userRepository.save(user)).thenReturn(user);

        User updatedUser = userService.update(user);

        verify(userRepository).save(user);
        assertTrue(updatedUser.getId().equals(2L));
    }

    @Test
    public void testDeleteUser() {
        User user = new User(3L, "Alice", "Smith", "alice.smith@example.com", "password", null, "Female", "Admin");

        doNothing().when(userRepository).delete(user);

        userService.delete(user);

        verify(userRepository).delete(user);
    }

    @Test
    public void testFindUser() {
        User user = new User(4L, "Alice", "Smith", "alice.smith@example.com", "password", null, "Female", "Admin");

        when(userRepository.findById(4L)).thenReturn(Optional.of(user));

        User foundUser = userService.find(4L);

        verify(userRepository).findById(4L);
        assertTrue(foundUser.getId().equals(4L));
    }

    @Test
    public void testFindByEmail() {
        User user = new User(5L, "Michael", "Jackson", "michael.jackson@example.com", "password123", null, "Male", "Admin");

        when(userRepository.findByEmail("michael.jackson@example.com")).thenReturn(user);

        User foundUser = userService.findByEmail("michael.jackson@example.com");

        verify(userRepository).findByEmail("michael.jackson@example.com");
        assertTrue(foundUser.getId().equals(5L));
    }

    @Test
    public void testAuthenticateUser() {
        User user = new User(6L, "Admin", "User", "admin@example.com", "password123", null, "Male", "Admin");

        when(userRepository.findByEmail("admin@example.com")).thenReturn(user);

        boolean authSuccess = userService.authenticate("admin@example.com", "password123");

        verify(userRepository).findByEmail("admin@example.com");
        assertTrue(authSuccess);
    }

    @Test
    public void testAuthenticateFailure() {
        when(userRepository.findByEmail("nonexistent@example.com")).thenReturn(null);

        boolean authSuccess = userService.authenticate("nonexistent@example.com", "wrongpassword");

        verify(userRepository).findByEmail("nonexistent@example.com");
        assertTrue(authSuccess == false);
    }

    @Test
    public void testDeleteInBatch() {
        List<User> users = Arrays.asList(
                new User(7L, "User1", "Smith", "user1@example.com", "password", null, "Male", "Admin"),
                new User(8L, "User2", "Doe", "user2@example.com", "password", null, "Female", "Admin")
        );

        doNothing().when(userRepository).deleteInBatch(users);

        userService.deleteInBatch(users);

        verify(userRepository).deleteInBatch(users);
    }
}
