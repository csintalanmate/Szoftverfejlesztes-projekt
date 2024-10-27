package com.csapatnev.casino.springapp;

import com.csapatnev.casino.models.User;
import com.csapatnev.casino.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringApp {

    public static void main(String[] args)
    {
        SpringApplication.run(SpringApp.class, "");
    }

    @Autowired
    UserRepository userRepository;
    public void printAll() {
        Iterable<User> users = userRepository.findAll();

        for (User p : users) {
            System.out.println("User: " + p);
        }

    }

}
