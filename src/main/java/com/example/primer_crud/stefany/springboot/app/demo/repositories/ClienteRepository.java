// ACCESO A DATOS
package com.example.primer_crud.stefany.springboot.app.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.primer_crud.stefany.springboot.app.demo.entities.ClienteEntity;


//Cliente : Entidad gestionada
//Integer: Tipo de dato de la clave primaria

//JpaRepository<ClienteEntity, Integer> = Hereda métodos CRUD automáticos
@Repository // Le dice a Spring "esto accede a la BD"
public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer> {

}
