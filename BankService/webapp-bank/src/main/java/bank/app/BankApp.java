package bank.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"bank.app"})
public class BankApp {

    public static void main(String[] args) {
        SpringApplication.run(BankApp.class, args);
    }
}
