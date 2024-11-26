package com.csapatnev.casino.controllers;

import com.csapatnev.casino.UserFX;
import com.csapatnev.casino.models.User;
import com.csapatnev.casino.services.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class AdminController implements Initializable {

    @Autowired
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @FXML
    private Button btnCreate;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<UserFX, Long> columnId;

    @FXML
    private TableColumn<UserFX, String> columnDob;

    @FXML
    private TableColumn<UserFX, String> columnEmail;

    @FXML
    private TableColumn<UserFX, String> columnFirstName;

    @FXML
    private TableColumn<UserFX, String> columnGender;

    @FXML
    private TableColumn<UserFX, String> columnLastName;

    @FXML
    private TableColumn<UserFX, String> columnPassword;

    @FXML
    private TableColumn<UserFX, String> columnRole;

    @FXML
    private TableView<UserFX> userTable;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<User> users = userService.findAll();  // Fetch users from the service

        // Convert to UserFX for JavaFX binding
        ObservableList<UserFX> userObservableList = FXCollections.observableArrayList();
        for (User user : users) {
            userObservableList.add(new UserFX(user));  // Convert User to UserFX
        }

        // Set the user list to the table
        userTable.setItems(userObservableList);

        // Set the cell value factories for UserFX
        columnId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        columnFirstName.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        columnLastName.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        columnEmail.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        columnDob.setCellValueFactory(cellData -> cellData.getValue().dobProperty().asString());
        columnPassword.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());
        columnGender.setCellValueFactory(cellData -> cellData.getValue().genderProperty());
        columnRole.setCellValueFactory(cellData -> cellData.getValue().roleProperty());
    }
}
