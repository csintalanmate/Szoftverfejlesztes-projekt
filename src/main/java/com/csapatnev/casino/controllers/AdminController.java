package com.csapatnev.casino.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class AdminController implements Initializable {

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
    private TableView<?> userTable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
