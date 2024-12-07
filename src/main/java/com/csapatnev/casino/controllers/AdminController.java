package com.csapatnev.casino.controllers;

import com.csapatnev.casino.AppContextProvider;
import com.csapatnev.casino.UserFX;
import com.csapatnev.casino.models.User;
import com.csapatnev.casino.services.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
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
    private Button btnLogin;

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
    private TableColumn<UserFX, Void> columnActions;


    @FXML
    private TableView<UserFX> userTable;

    @FXML
    void switchToLogin() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
        loader.setControllerFactory(AppContextProvider::getBean);
        Parent adminRoot = loader.load();

        Stage stage = (Stage) btnLogin.getScene().getWindow();
        stage.setScene(new Scene(adminRoot));
    }


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

        columnActions.setCellFactory(new Callback<TableColumn<UserFX, Void>, TableCell<UserFX, Void>>() {
            @Override
            public TableCell<UserFX, Void> call(final TableColumn<UserFX, Void> param) {
                return new TableCell<>() {
                    private final Button btnEdit = new Button("Edit");
                    private final Button btnDelete = new Button("Delete");

                    {


                        btnEdit.setOnAction(event -> {
                            UserFX user = getTableView().getItems().get(getIndex());
                            try {
                                handleEditAction(user);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        });
                        // Delete button action
                        btnDelete.setOnAction(event -> {
                            UserFX user = getTableView().getItems().get(getIndex());
                            handleDeleteAction(user);
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty) {
                            setGraphic(null);
                        } else {
                            // Layout for buttons
                            HBox hbox = new HBox(5, btnEdit, btnDelete);
                            setGraphic(hbox);
                        }
                    }
                };
            }
        });
    }

    private void handleDeleteAction(UserFX user) {
        // Confirm the delete action
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Törlés véglegesítése");
        alert.setHeaderText("Biztos törölni akarod ezt a usert?");
        alert.setContentText(user.firstNameProperty() + " " + user.lastNameProperty());

        // Perform delete if confirmed
        if (alert.showAndWait().get() == ButtonType.OK) {
            userService.delete(user.idProperty().get());
            refreshTable();
        }
    }

    private void handleEditAction(UserFX user) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Update.fxml"));

        loader.setControllerFactory(AppContextProvider::getBean);

        Parent adminRoot = loader.load();
        UpdateController controller = loader.getController();
        controller.setUser(user); // Pass user data to the UpdateController

        Stage stage = (Stage) userTable.getScene().getWindow();
        stage.setScene(new Scene(adminRoot));
    }

//        try {
//            // Load the Edit User FXML
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Update.fxml"));
//            loader.setControllerFactory(AppContextProvider::getBean);
//            Parent root = loader.load();
////
////            // Pass the selected user to the EditController
////            UpdateController controller = loader.getController();
////            controller.setUser(user);
//
//            // Show the Edit dialog
//            Stage stage = new Stage();
//            stage.setScene(new Scene(root));
//            stage.setTitle("Edit User");
//            stage.showAndWait();
//
//            // Refresh the table after editing
//            refreshTable();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    private void refreshTable() {
        List<User> users = userService.findAll();  // Fetch updated user list
        ObservableList<UserFX> userObservableList = FXCollections.observableArrayList();

        for (User user : users) {
            userObservableList.add(new UserFX(user));  // Convert to UserFX
        }

        userTable.setItems(userObservableList);
    }


    @FXML
    void login(ActionEvent event) throws IOException{
        switchToLogin();
    }
}
