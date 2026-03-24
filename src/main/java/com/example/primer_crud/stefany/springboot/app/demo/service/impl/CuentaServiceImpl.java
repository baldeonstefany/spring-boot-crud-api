package com.example.primer_crud.stefany.springboot.app.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.primer_crud.stefany.springboot.app.demo.entities.ClienteEntity;
import com.example.primer_crud.stefany.springboot.app.demo.entities.CuentaEntity;
import com.example.primer_crud.stefany.springboot.app.demo.repositories.ClienteRepository;
import com.example.primer_crud.stefany.springboot.app.demo.repositories.CuentaRepository;
import com.example.primer_crud.stefany.springboot.app.demo.service.CuentaService;

/*
 * SERVICE PARA CUENTAS BANCARIAS
 * 
 * Contiene la LÓGICA DE NEGOCIO para cuentas
 * Comunica entre CONTROLLER ↔ REPOSITORY
 * 
 * @Service → Le dice a Spring que esta clase maneja lógica de negocio
 */
@Service
public class CuentaServiceImpl implements CuentaService {

    // ========== INYECCIÓN DE DEPENDENCIAS ==========
    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    // ========== MÉTODOS HEREDADOS DE LA INTERFAZ ==========
    /*@Override
    public void ELIMINAR(Integer idCliente, Long idCuenta) {
        /* 
        // Lógica para eliminar cuenta
        ClienteEntity cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        CuentaEntity cuentaEntity = cliente.getCuentas()
                .stream()
                .filter(c -> c.getId().equals(idCuenta))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        cliente.getCuentas().remove(cuentaEntity); // Eliminar la cuenta de la lista
        cuentaRepository.delete(cuentaEntity); // Eliminar la cuenta de la base de datos
    }
    */
    

    /*
     * MÉTODO 1: Obtener TODAS las cuentas
     * 
     * @return List<CuentaEntity> - Lista con todas las cuentas
     */
    @Override
    public List<CuentaEntity> BUSCARTODOS() {
        return cuentaRepository.findAll();
    }

    /*
     * MÉTODO 2: Obtener una cuenta por ID
     * 
     * @param id - ID de la cuenta
     * 
     * @return Optional<CuentaEntity> - Cuenta encontrada o vacío
     */
    @Override
    public Optional<CuentaEntity> BUSCARPORID(Long id) {
        return cuentaRepository.findById(id);
    }

    /**
     * MÉTODO 3: Guardar o actualizar una cuenta
     * 
     * @param cuenta - La cuenta a guardar
     * @return CuentaEntity - La cuenta guardada en BD
     * 
     *         VALIDACIONES: Verifica que los datos sean correctos
     */
    @Override
    public CuentaEntity GUARDAR(CuentaEntity cuenta) {

        // Validar que la cuenta no sea nula
        if (cuenta == null) {
            throw new IllegalArgumentException("❌ La cuenta no puede ser nula");
        }

        // Validar que el cliente existe
        if (cuenta.getCliente() == null || cuenta.getCliente().getId() == null) {
            throw new IllegalArgumentException("❌ El cliente de la cuenta es obligatorio");
        }

        ClienteEntity cliente = clienteRepository.findById(cuenta.getCliente().getId())
                .orElseThrow(() -> new IllegalArgumentException("❌ El cliente no existe"));

        cuenta.setCliente(cliente);
        
        // Validar datos obligatorios
        if (!cuenta.isSaldoValido()) {
            throw new IllegalArgumentException("❌ El saldo no puede ser negativo");
        }

        // Si es nueva, asignar fecha de apertura
        if (cuenta.getId() == null) {
            cuenta.setFechaApertura(LocalDateTime.now().toString());
            if (cuenta.getEstado() == null) {
                cuenta.setEstado("ACTIVA");
            }
        }

        // ✅ Guardar en BD
        return cuentaRepository.save(cuenta);
    }

    /**
     * MÉTODO 4: Eliminar una cuenta por ID
     * 
     * @param id - ID de la cuenta a eliminar
     */
    @Override
    public void ELIMINAR(Long id) {

        // Validar que el ID no sea nulo
        if (id == null) {
            throw new IllegalArgumentException("❌ El ID no puede ser nulo");
        }

        // Validar que la cuenta existe
        if (!cuentaRepository.existsById(id)) {
            throw new IllegalArgumentException("❌ La cuenta con ID " + id + " no existe");
        }

        // ✅ Eliminar
        cuentaRepository.deleteById(id);
    }

}
