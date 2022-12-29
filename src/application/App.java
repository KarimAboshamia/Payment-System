package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import api.Authentication;
import api.BalanceEndPoints;
import api.*;
import managers.AuthenticationManager;

@ComponentScan(basePackageClasses = Authentication.class)
@ComponentScan(basePackageClasses = BalanceEndPoints.class)
@ComponentScan(basePackageClasses = SearchServices.class)
@ComponentScan(basePackageClasses = ServiceDiscount.class)
@ComponentScan(basePackageClasses = Refund.class)


@SpringBootApplication
public class App {
	public static void main(String [] args) {
		SpringApplication.run(App.class, args);
	}

}
