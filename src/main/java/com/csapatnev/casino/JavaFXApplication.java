package com.csapatnev.casino;

import com.csapatnev.casino.models.User;
import com.csapatnev.casino.repositories.UserRepository;
import com.csapatnev.casino.utils.AdminInserter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class JavaFXApplication extends Application {

    private ConfigurableApplicationContext springContext;


    @Override
    public void init() {
        springContext = new SpringApplicationBuilder(CasinoApplication.class).run();
        admin();
    }

    private void admin() {
        UserRepository userRepository = springContext.getBean(UserRepository.class); // Fetch the bean
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


    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
        loader.setControllerFactory(springContext::getBean); // Use Spring to inject controllers
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.setTitle("Casino Application");
        primaryStage.show();
    }

    @Override
    public void stop() {
        System.out.println("Closing application...");
        springContext.close(); // Close Spring context
        CasinoApplication.stopH2ConsoleServer(); // Stop the H2 server
    }

    public static void main(String[] args) {
        launch(args);


    }
}
