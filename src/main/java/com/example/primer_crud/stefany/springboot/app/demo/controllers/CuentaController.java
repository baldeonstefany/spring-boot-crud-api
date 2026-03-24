package com.example.primer_crud.stefany.springboot.app.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.primer_crud.stefany.springboot.app.demo.entities.CuentaEntity;
import com.example.primer_crud.stefany.springboot.app.demo.service.impl.CuentaServiceImpl;

@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {

    @Autowired
    private CuentaServiceImpl cuentaServiceImpl;

    // GET - Obtener todas las cuentas
    @GetMapping
    public List<CuentaEntity> obtenerTodas(){
        return cuentaServiceImpl.BUSCARTODOS();
    }

    // GET - Obtener cuenta por ID
    @GetMapping("/{id}")
    public Optional<CuentaEntity> obtenerPorId(@PathVariable Long id) {
        return cuentaServiceImpl.BUSCARPORID(id);
    }

    // POST - Crear nueva cuenta
    @PostMapping
    public CuentaEntity crear(@RequestBody CuentaEntity cuenta) {
        return cuentaServiceImpl.GUARDAR(cuenta);
    }

    // PUT - Actualizar cuenta
    @PutMapping("/{id}")
    public Optional<CuentaEntity> actualizar(@PathVariable Long id, @RequestBody CuentaEntity cuentaActualizada) {
        return cuentaServiceImpl.BUSCARPORID(id).map(cuenta -> {
            return cuentaServiceImpl.GUARDAR(cuentaActualizada);
        });
    }

    // DELETE - Eliminar cuenta
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        cuentaServiceImpl.ELIMINAR(id);
    }




}
