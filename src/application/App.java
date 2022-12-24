package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import api.Authentication;
import api.BalanceEndPoints;
import managers.AuthenticationManager;

@ComponentScan(basePackageClasses = Authentication.class)
@ComponentScan(basePackageClasses = BalanceEndPoints.class)

@SpringBootApplication
public class App {
	public static void main(String [] args) {
		SpringApplication.run(App.class, args);
	}

}
