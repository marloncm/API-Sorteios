package com.dell.desafio.desafiosorteio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "*")
public class DesafioSorteioApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesafioSorteioApplication.class, args);
    }

}
