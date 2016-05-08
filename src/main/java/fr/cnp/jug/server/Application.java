package fr.cnp.jug.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("fr.cnp.jug")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
