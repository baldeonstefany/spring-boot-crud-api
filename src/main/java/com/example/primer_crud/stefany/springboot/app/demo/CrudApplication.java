package com.example.primer_crud.stefany.springboot.app.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching // Habilita el soporte de caché en la aplicación, lo que permite utilizar anotaciones como @Cacheable, @CachePut y @CacheEvict para gestionar la caché de manera declarativa.
@SpringBootApplication
public class CrudApplication {
    public static void main(String[] args) {
        SpringApplication.run(CrudApplication.class, args);
    }
}


