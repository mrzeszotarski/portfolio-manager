package pl.mrzeszotarski.stock.portfoliooptimiser.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class ApplicationMain {

    public static void main(String[] args){
        SpringApplication.run(ApplicationMain.class);
    }
}
