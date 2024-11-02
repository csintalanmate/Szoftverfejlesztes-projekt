package com.csapatnev.casino;

import com.csapatnev.casino.JavaFXApplication;
import org.h2.tools.Server;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class CasinoApplication {
	public static void main(String[] args) {
		startH2ConsoleServer(); // Start H2 console server manually
		JavaFXApplication.main(args);
	}

	private static void startH2ConsoleServer() {
		try {
			Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
