/*Configuracion de CORS:
Permite solicitudes de diferentes dominios que accedan al API
*/
package com.example.primer_crud.stefany.springboot.app.demo.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Permitir acceso a todos los endpoints de /api
                .allowedOrigins("*") // Permitir cualquier origen
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos permitidos
                .allowCredentials(true); // Permitir credenciales (si es necesario)
    }
}
