package com.csapatnev.casino.utils;

import com.csapatnev.casino.models.User;
import com.csapatnev.casino.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class AdminInserter {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public void init() {
        // Check if admin already exists
        if (userRepository.findById(1L).isEmpty()) {
            User admin = new User();
            admin.setFirstName("admin");
            admin.setLastName("admin");
            admin.setEmail("admin@admin");
            admin.setPassword("admin"); // You should hash this password in a real application
            admin.setDob(null);
            admin.setGender("Male");
            admin.setRole("ADMIN");

            userRepository.save(admin); // Save the admin user
            System.out.println("Admin user inserted successfully.");
        }
    }
}
