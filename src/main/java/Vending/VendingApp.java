package Vending;

import Vending.controller.VendingController;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class VendingApp {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        appContext.scan("Vending");
        appContext.refresh();

        VendingController controller = appContext.getBean("vendingController", VendingController.class);
        controller.run();
    }
}
