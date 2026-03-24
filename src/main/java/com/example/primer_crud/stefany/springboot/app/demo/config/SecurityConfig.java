package com.example.primer_crud.stefany.springboot.app.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.primer_crud.stefany.springboot.app.demo.security.jwt.JwtFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
            // Desactivar CSRF para las API REST
            .csrf(AbstractHttpConfigurer::disable)
            // Desactivar el login por formulario y la autenticación básica
            .formLogin(AbstractHttpConfigurer::disable)
            .httpBasic(AbstractHttpConfigurer::disable)

            // Stateless (sin sesión, JWT)
            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            // Configuración de autorización
            .authorizeHttpRequests(auth -> auth
                // Permitir acceso a Swagger
                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                // Permitir acceso a /auth/** sin autenticación (si aplica)
                .requestMatchers("/auth/**").permitAll()
                // Requiere autenticación para cualquier otra solicitud
                .anyRequest().authenticated() 
            )

            // Agregar el filtro JWT antes de UsernamePasswordAuthenticationFilter
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)

            .build();
    }
}


