package com.example.primer_crud.stefany.springboot.app.demo.config;
import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConectionDataBaseTest {
 // Este método se ejecuta al iniciar la aplicación
    @Bean
    CommandLineRunner testDatabaseConnection(DataSource dataSource) {
        return args -> {
            try (Connection connection = dataSource.getConnection()) {

                System.out.println("=====================================");
                System.out.println("✅ CONEXIÓN A LA BASE DE DATOS EXITOSA");
                System.out.println("URL: " + connection.getMetaData().getURL());
                System.out.println("Usuario: " + connection.getMetaData().getUserName());
                System.out.println("=====================================");

            } catch (Exception e) {
                System.out.println("❌ ERROR DE CONEXIÓN A LA BASE DE DATOS");
                e.printStackTrace();
            }
        };
    }
}
