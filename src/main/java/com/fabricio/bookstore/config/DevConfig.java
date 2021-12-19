package com.fabricio.bookstore.config;

import com.fabricio.bookstore.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean
    public void instanciaBaseDeDados() {
        if(strategy.equalsIgnoreCase("create")) {
            dbService.inicializaBaseDeDados();
        }
    }

}
