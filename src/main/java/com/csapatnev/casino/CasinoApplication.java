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
public class CasinoApplication extends Application {

	private ConfigurableApplicationContext springContext;
	private Parent rootNode;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void init() throws Exception {
		springContext = SpringApplication.run(CasinoApplication.class);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));
		fxmlLoader.setControllerFactory(springContext::getBean);
		rootNode = fxmlLoader.load();
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(new Scene(rootNode));
		stage.setTitle("Casino Application");
		stage.show();
	}

	@Override
	public void stop() throws Exception {
		springContext.close();
	}
}
