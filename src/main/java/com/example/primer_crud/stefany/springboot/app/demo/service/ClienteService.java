package com.example.primer_crud.stefany.springboot.app.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.primer_crud.stefany.springboot.app.demo.entities.ClienteEntity;

//import jakarta.persistence.criteria.CriteriaBuilder.In;



public interface ClienteService {

  //METODOS

  /*List -> coleccion de objetos
   *BUSCARTODOS(),BUSCARPORID(),GUARDAR(),ELMINAR() -> nombre de los metodos
   *Optional -> contenedor que puede o no contener un valor
   *Integer id, clienteEntity -> parametro que recibe el metodo
   */


    List<ClienteEntity> BUSCARTODOS();
    Optional<ClienteEntity> BUSCARPORID(Integer id);
    ClienteEntity GUARDAR(ClienteEntity clienteEntity);
    ClienteEntity ACTUALIZAR(Integer id, ClienteEntity clienteEntity);
    void ELMINAR(Integer id);

}
    