package com.csapatnev.casino.services;

import com.csapatnev.casino.generic.GenericService;
import com.csapatnev.casino.models.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface UserService extends GenericService<User> {
    boolean authenticate(String email, String password);

    User findByEmail(String email);
}
