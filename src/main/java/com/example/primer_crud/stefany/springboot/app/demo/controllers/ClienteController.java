package com.example.primer_crud.stefany.springboot.app.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.primer_crud.stefany.springboot.app.demo.entities.ClienteEntity;
import com.example.primer_crud.stefany.springboot.app.demo.service.impl.ClienteServiceImpl;

@RestController // Indica que esta clase es un controlador REST
@RequestMapping("/api/clientes") // Ruta base para este controlador
public class ClienteController {

    // ========== INYECCIÓN DE DEPENDENCIAS ==========
    // @Autowired: Le dice a Spring "dame una instancia de ClienteServiceImpl"
    // private ClienteServiceImpl clienteServiceImpl : Declara la variable del
    // servicio
    // IMPORTANTE: Inyectamos el SERVICIO, NO el repositorio
    // ¿Por qué? Porque el servicio contiene la lógica de negocio
    @Autowired
    private ClienteServiceImpl clienteServiceImpl;

    // ========== GET - OBTENER TODOS ==========
    // @GetMapping: Mapea GET /api/clientes
    // public List<Cliente>: Devuelve una lista de clientes en JSON
    @GetMapping("/todos")
    public List<ClienteEntity> obtenerTodos() {
        // Llama al servicio para obtener todos los clientes
        // El servicio llama al repositorio
        // El repositorio accede a la BD
        // Retorna: List<Cliente>
        return clienteServiceImpl.BUSCARTODOS();  
    }

    // ========== GET - OBTENER POR ID ==========
    // @GetMapping("/{id}"): Mapea GET /api/clientes/1
    // @PathVariable Integer id: Extrae el ID de la URL
    // Optional<ClienteEntity>: Puede estar vacío o contener un ClienteEntity

    @GetMapping("{id}")
    public Optional<ClienteEntity> obtenerPorId(@PathVariable Integer id) {
        // Llama al servicio pasando el ID
        // Busca en la BD
        // Retorna: Optional<ClienteEntity> (vacío si no existe)
        return clienteServiceImpl.BUSCARPORID(id);
    }

    // ========== POST - CREAR NUEVO ==========
    // @PostMapping: Mapea POST /api/clientes
    // @RequestBody ClienteEntity cliente: Recibe un JSON y lo convierte a objeto
    // ClienteEntity
    // public ClienteEntity: Devuelve el cliente guardado con ID asignado
    @PostMapping
    public ClienteEntity crear(@RequestBody ClienteEntity cliente) {
        // Llama al servicio para guardar
        // El servicio llama al repositorio
        // El repositorio inserta en la BD y asigna un ID
        // Retorna: El ClienteEntity guardado con su ID
        return clienteServiceImpl.GUARDAR(cliente);
    }

    // ========== PUT - ACTUALIZAR ==========
    // @PutMapping("/{id}"): Mapea PUT /api/clientes/1
    // @PathVariable Integer id: ID del cliente a actualizar
    // @RequestBody ClienteEntity clienteActualizado: Nuevos datos del cliente
    @PutMapping("/{id}")
    public ResponseEntity<ClienteEntity> actualizar(@PathVariable Integer id,
            @RequestBody ClienteEntity clienteActualizado) {
        // Llama al servicio para actualizar
        // El servicio llama al repositorio
        // El repositorio actualiza en la BD
        ClienteEntity clienteExistente = clienteServiceImpl.ACTUALIZAR(id, clienteActualizado);
        // ResponseEntity permite controlar el código HTTP y el cuerpo de la respuesta
        // Aquí devolvemos:
        // - HTTP 200 OK
        // - El cliente actualizado en el body
        return ResponseEntity.ok(clienteExistente);
    }

    // ========== DELETE - ELIMINAR ==========
    // @DeleteMapping("/{id}"): Mapea DELETE /api/clientes/1
    // @PathVariable Integer id: ID del cliente a eliminar
    // void: No retorna nada
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        // Llama al servicio para eliminar
        // El servicio llama al repositorio
        // El repositorio borra de la BD
        clienteServiceImpl.ELMINAR(id);
    }

}
