package com.csapatnev.casino;

import com.csapatnev.casino.models.User;
import javafx.beans.property.*;
        import java.time.LocalDate;

public class UserFX {
    private final LongProperty idProperty = new SimpleLongProperty();
    private final StringProperty firstNameProperty = new SimpleStringProperty();
    private final StringProperty lastNameProperty = new SimpleStringProperty();
    private final StringProperty emailProperty = new SimpleStringProperty();
    private final StringProperty passwordProperty = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> dobProperty = new SimpleObjectProperty<>();
    private final StringProperty genderProperty = new SimpleStringProperty();
    private final StringProperty roleProperty = new SimpleStringProperty();

    // Constructor for UI Binding
    public UserFX(User user) {
        this.idProperty.set(user.getId());
        this.firstNameProperty.set(user.getFirstName());
        this.lastNameProperty.set(user.getLastName());
        this.emailProperty.set(user.getEmail());
        this.passwordProperty.set(user.getPassword());
        this.dobProperty.set(user.getDob());
        this.genderProperty.set(user.getGender());
        this.roleProperty.set(user.getRole());
    }

    // Getters for JavaFX properties
    public LongProperty idProperty() {
        return idProperty;
    }

    public StringProperty firstNameProperty() {
        return firstNameProperty;
    }

    public StringProperty lastNameProperty() {
        return lastNameProperty;
    }

    public StringProperty emailProperty() {
        return emailProperty;
    }

    public StringProperty passwordProperty() {
        return passwordProperty;
    }

    public ObjectProperty<LocalDate> dobProperty() {
        return dobProperty;
    }

    public StringProperty genderProperty() {
        return genderProperty;
    }

    public StringProperty roleProperty() {
        return roleProperty;
    }
}
