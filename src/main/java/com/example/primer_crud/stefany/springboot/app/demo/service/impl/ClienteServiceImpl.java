package com.example.primer_crud.stefany.springboot.app.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.primer_crud.stefany.springboot.app.demo.entities.ClienteEntity;
import com.example.primer_crud.stefany.springboot.app.demo.repositories.ClienteRepository;
import com.example.primer_crud.stefany.springboot.app.demo.service.ClienteService;

//import jakarta.persistence.criteria.CriteriaBuilder.In;

/*
 * SERVICE PARA CLIENTES
 * 
 * Contiene la LÓGICA DE NEGOCIO para clientes
 * Comunica entre CONTROLLER ↔ REPOSITORY
 * 
 * @Service → Le dice a Spring que esta clase maneja lógica de negocio
 */
@Service
public class ClienteServiceImpl implements ClienteService {

    // ========== INYECCIÓN DE DEPENDENCIAS ==========
    @Autowired
    private ClienteRepository clienteRepository;

    // ========== MÉTODOS HEREDADOS DE LA INTERFAZ ==========

    /**
     * MÉTODO 1: Obtener TODOS los clientes
     * 
     * @return List<ClienteEntity> - Lista con todos los clientes
     */
    @Cacheable(value = "clientesLista", key = "'all'") // Cachea el resultado de este método con la clave
                                                       // "clientesLista::all"
    @Override
    public List<ClienteEntity> BUSCARTODOS() {
        return clienteRepository.findAll();
    }

    /**
     * MÉTODO 2: Obtener un cliente por ID
     * 
     * @param id - ID del cliente
     * @return Optional<ClienteEntity> - Cliente encontrado o vacío
     */
    @Cacheable(value = "clientesPorId", key = "#id") // Cachea el resultado de este método con la clave "clientes::[id]"
    @Override
    public Optional<ClienteEntity> BUSCARPORID(Integer id) {
        return clienteRepository.findById(id);
    }

    /**
     * MÉTODO 3: Guardar o actualizar un cliente
     * 
     * @param cliente - El cliente a guardar
     * @return ClienteEntity - El cliente guardado en BD
     * 
     *         VALIDACIONES: Verifica que los datos sean correctos
     */

    @CachePut(value = "clientePorid", key = "#clienteEntity.id") // Cachea el resultado de este método con la clave
                                                                 // "clientePorId::[cliente.id]"
    @Override
    public ClienteEntity GUARDAR(ClienteEntity clienteEntity) {

        // Validar que el cliente no sea nulo
        if (clienteEntity == null) {
            throw new IllegalArgumentException("❌ El cliente no puede ser nulo");
        }

        // Validar datos obligatorios
        if (clienteEntity.getNumeroDocumento() == null || clienteEntity.getNumeroDocumento().isEmpty()) {
            throw new IllegalArgumentException("❌ El número de documento es obligatorio");
        }

        if (clienteEntity.getNombres() == null || clienteEntity.getNombres().isEmpty()) {
            throw new IllegalArgumentException("❌ El nombre es obligatorio");
        }

        if (clienteEntity.getApellidos() == null || clienteEntity.getApellidos().isEmpty()) {
            throw new IllegalArgumentException("❌ El apellido es obligatorio");
        }

        // Usar método de Entity para validar email
        if (clienteEntity.getEmail() != null && !clienteEntity.getEmail().isEmpty()) {
            if (!clienteEntity.isEmailValido()) {
                throw new IllegalArgumentException("❌ El email es inválido");
            }
        }

        // Si es cliente nuevo, asignar fecha de registro
        if (clienteEntity.getId() == null) {
            clienteEntity.setFechaRegistro(LocalDateTime.now()); // Usar LocalDateTime.now() en lugar de new Date()
            if (clienteEntity.getUsuarioRegistro() == null) {
                clienteEntity.setUsuarioRegistro("SISTEMA");
            }
        }

        // ✅ Guardar en BD
        return clienteRepository.save(clienteEntity);
    }

    @CachePut(value = "clientePorid", key = "#clienteEntity.id")
    @Override
    public ClienteEntity ACTUALIZAR(Integer id, ClienteEntity clienteEntity) {

        // Buscar el cliente existente en la BD
        // Si existe el id te devuelve clienteEntity, si no lanza excepción
        // clienteExistente -> que retorna JPA sí está “gestionado” por Hibernate
        // (attached/managed).
        ClienteEntity clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("❌ Cliente no encontrado"));

        // 2Actualizar SOLO los campos permitidos
        clienteExistente.setNombres(clienteEntity.getNombres());
        clienteExistente.setApellidos(clienteEntity.getApellidos());
        clienteExistente.setEmail(clienteEntity.getEmail());
        clienteExistente.setTelefono(clienteEntity.getTelefono());
        clienteExistente.setEstado(clienteEntity.getEstado());

        // ❌ JAMÁS actualizar:
        // clienteExistente.setId(...)
        // clienteExistente.setNumeroDocumento(...)
        // clienteExistente.setTipoDocumento(...)

        // 3️⃣ Guardar EL ENTITY EXISTENTE (Hibernate hace UPDATE)
        return clienteRepository.save(clienteExistente);
    }

    /*
     * MÉTODO 4: Eliminar un cliente por ID
     * 
     * @param id - ID del cliente a eliminar
     */
    @CacheEvict(value = "clientePorid", key = "#id") // Elimina la entrada de caché con la clave "clientePorId::[id]"
                                                     // cuando se elimina el cliente
    @Override
    public void ELMINAR(Integer id) {

        // Validar que el ID no sea nulo
        if (id == null) {
            throw new IllegalArgumentException("❌ El ID no puede ser nulo");
        }

        // Validar que el cliente existe
        if (!clienteRepository.existsById(id)) {
            throw new IllegalArgumentException("❌ El cliente con ID " + id + " no existe");
        }

        // ✅ Eliminar
        clienteRepository.deleteById(id);
    }

    /*
     * Usa el método getNombreCompleto() que definimos en Entity
     */
    public String obtenerNombreCompleto(Integer id) {
        Optional<ClienteEntity> cliente = clienteRepository.findById(id);

        if (cliente.isPresent()) {
            return cliente.get().getNombreCompleto();
        }

        throw new RuntimeException("❌ Cliente con ID " + id + " no encontrado");
    }

    /**
     * MÉTODO ADICIONAL: Verificar si un cliente está activo
     * 
     * @param id - ID del cliente
     * @return boolean - true si está activo, false si no
     */
    public boolean esClienteActivo(Integer id) {
        Optional<ClienteEntity> cliente = clienteRepository.findById(id);

        if (cliente.isPresent()) {
            return cliente.get().isActivo();
        }

        return false;
    }

}
