package edu.tcu.cs.hw1problem5;

import edu.tcu.cs.hw1problem5.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Hw1problem5Application {

    public static void main(String[] args) {
        SpringApplication.run(Hw1problem5Application.class, args);
    }
    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }
}
