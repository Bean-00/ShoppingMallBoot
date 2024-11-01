package net.study.shoppingmallboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

@SpringBootApplication
public class ShoppingMallBootApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ShoppingMallBootApplication.class);
        application.addListeners(new ApplicationPidFileWriter());
        application.run(args);
    }

}
