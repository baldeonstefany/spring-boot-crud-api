package com.example.primer_crud.stefany.springboot.app.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.primer_crud.stefany.springboot.app.demo.entities.CuentaEntity;

public interface CuentaService {
    
    List<CuentaEntity> BUSCARTODOS();
    Optional<CuentaEntity> BUSCARPORID(Long id);
    CuentaEntity GUARDAR(CuentaEntity cuenta);
    void ELIMINAR(Long id);
}
