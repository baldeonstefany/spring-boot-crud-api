package com.example.primer_crud.stefany.springboot.app.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.primer_crud.stefany.springboot.app.demo.entities.CuentaEntity;

@Repository
public interface CuentaRepository extends JpaRepository<CuentaEntity, Long> {

}
