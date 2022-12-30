package api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackageClasses = Authentication.class)
@ComponentScan(basePackageClasses = BalanceEndPoints.class)

@ComponentScan(basePackageClasses = SearchServices.class)
@ComponentScan(basePackageClasses = ServiceDiscount.class)
@ComponentScan(basePackageClasses = Refund.class)
@ComponentScan(basePackageClasses = DiscountEndPoint.class)
@ComponentScan(basePackageClasses = Payment.class)
@ComponentScan(basePackageClasses = TransactionsEndPoint.class)


@SpringBootApplication
public class App {
	public static void main(String [] args) {
		SpringApplication.run(App.class, args);
	}

}
