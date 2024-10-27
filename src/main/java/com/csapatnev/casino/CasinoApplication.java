package com.csapatnev.casino;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import javafx.scene.Parent;

@SpringBootApplication
@EnableScheduling
public class CasinoApplication {
	public static void main(String[] args) {
		JavaFXApplication.main(args);
	}
}

