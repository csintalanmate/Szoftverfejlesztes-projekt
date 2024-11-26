package com.csapatnev.casino.controllers;

import com.csapatnev.casino.models.User;
import com.csapatnev.casino.services.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class AdminController implements Initializable {

    @Autowired
    private final UserService userService;

    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, Long> columnId;
    @FXML
    private TableColumn<User, String> columnFirstName;
    @FXML
    private TableColumn<User, String> columnLastName;
    @FXML
    private TableColumn<User, String> columnEmail;
    @FXML
    private TableColumn<User, String> columnDob;

    @FXML
    private Button btnCreate;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;

    private ObservableList<User> userList = FXCollections.observableArrayList();

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize TableView columns
        columnId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        columnFirstName.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        columnLastName.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        columnEmail.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        columnDob.setCellValueFactory(cellData -> cellData.getValue().dobProperty());

        // Load users into the TableView
        loadUsers();

        // Handle selection events (for Update/Delete)
        userTable.setOnMouseClicked(event -> handleUserSelection(event));
    }

    // Load users from the database
    private void loadUsers() {
        userList.clear();
        userList.addAll(userService.findAllUsers()); // Fetch all users from the service
        userTable.setItems(userList);
    }

    // Create a new user (for now just showing an alert as an example)
    @FXML
    private void createUser() {
        // Create user logic, or show a Create form dialog
        showAlert("Create User", "User creation feature coming soon!");
    }

    // Update selected user
    @FXML
    private void updateUser() {
        User selectedUser = userTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            // Perform update logic, maybe open a form to edit the selected user's data
            showAlert("Update User", "Update feature coming soon!");
        } else {
            showAlert("Error", "No user selected!");
        }
    }

    // Delete selected user
    @FXML
    private void deleteUser() {
        User selectedUser = userTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            userService.deleteUser(selectedUser.getId()); // Assuming a delete method in UserService
            loadUsers(); // Reload the table after deleting the user
            showAlert("Delete User", "User deleted successfully!");
        } else {
            showAlert("Error", "No user selected!");
        }
    }

    // Handle mouse event for selecting a user
    private void handleUserSelection(MouseEvent event) {
        User selectedUser = userTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            // You can show user details in another section of the UI if needed
        }
    }

    // Helper method to show alerts
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
