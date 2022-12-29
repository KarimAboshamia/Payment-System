package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import api.Authentication;
import api.BalanceEndPoints;
import api.DiscountEndPoint;
import api.Payment;
import api.TransactionsEndPoint;
import managers.AuthenticationManager;

@ComponentScan(basePackageClasses = Authentication.class)
@ComponentScan(basePackageClasses = BalanceEndPoints.class)
@ComponentScan(basePackageClasses = DiscountEndPoint.class)
@ComponentScan(basePackageClasses = Payment.class)
@ComponentScan(basePackageClasses = TransactionsEndPoint.class)


@SpringBootApplication
public class App {
	public static void main(String [] args) {
		SpringApplication.run(App.class, args);
	}

}
