package com.cine_reserva_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class CineReservaBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(CineReservaBackendApplication.class, args);
    }
}
