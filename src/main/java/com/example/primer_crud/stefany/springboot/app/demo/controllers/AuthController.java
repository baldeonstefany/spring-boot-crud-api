package com.example.primer_crud.stefany.springboot.app.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.primer_crud.stefany.springboot.app.demo.security.jwt.JwtUtil;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController{

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> body) {

        String username = body.get("username");
        String password = body.get("password");

        
        if (!"admin".equals(username) || !"1234".equals(password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Credenciales inválidas"));
        }

        String token = jwtUtil.generateToken(username);

        return ResponseEntity.ok(Map.of("token", token));
    }
}

