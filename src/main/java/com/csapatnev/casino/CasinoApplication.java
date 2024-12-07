package com.csapatnev.casino;

import com.csapatnev.casino.JavaFXApplication;
import com.csapatnev.casino.utils.AdminInserter;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class CasinoApplication {
	private static Server h2Server; // Store reference to the H2 server



	public static void main(String[] args) {

		startH2ConsoleServer(); // Start H2 console server manually

		JavaFXApplication.main(args); // Launch JavaFX application
	}

	public static void stopH2ConsoleServer() {
		if (h2Server != null && h2Server.isRunning(false)) {
			System.out.println("Stopping H2 console server...");
			h2Server.stop();
		}
	}

	private static void startH2ConsoleServer() {
		try {
			if (h2Server == null || !h2Server.isRunning(false)) {
				h2Server = Server.createWebServer("-web", "-webAllowOthers", "-webPort", "46590").start();
				System.out.println("H2 console server started on port 46590");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
