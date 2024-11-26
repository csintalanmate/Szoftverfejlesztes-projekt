package com.csapatnev.casino.controllers;

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
    private TableColumn<?, ?> columnDob;

    @FXML
    private TableColumn<?, ?> columnEmail;

    @FXML
    private TableColumn<?, ?> columnFirstName;

    @FXML
    private TableColumn<?, ?> columnGender;

    @FXML
    private TableColumn<?, ?> columnId;

    @FXML
    private TableColumn<?, ?> columnLastName;

    @FXML
    private TableColumn<?, ?> columnPassword;

    @FXML
    private TableColumn<?, ?> columnRole;

    @FXML
    private TableView<User> userTable;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<User> users = userService.findAll();  // Adjust this method as per your implementation

        // Convert the list of users to an ObservableList
        ObservableList<User> userObservableList = FXCollections.observableArrayList(users);

        // Set the user list to the table
        userTable.setItems(userObservableList);

        // Set the cell value factories (mapping each column to the respective field in the User object)
        columnId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        columnFirstName.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        columnLastName.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        columnEmail.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        columnDob.setCellValueFactory(cellData -> cellData.getValue().dobProperty());
        columnPassword.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());
        columnGender.setCellValueFactory(cellData -> cellData.getValue().genderProperty());
        columnRole.setCellValueFactory(cellData -> cellData.getValue().roleProperty());
    }
}
