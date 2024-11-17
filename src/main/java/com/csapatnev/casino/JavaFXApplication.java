package com.csapatnev.casino;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class JavaFXApplication extends Application {

    private ConfigurableApplicationContext springContext;

    @Override
    public void init() {
        springContext = new SpringApplicationBuilder(CasinoApplication.class).run();
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
