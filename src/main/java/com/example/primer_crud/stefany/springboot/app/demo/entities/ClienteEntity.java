package com.example.primer_crud.stefany.springboot.app.demo.entities;

import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
import java.time.LocalDateTime;

//import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor // crea un constructor con todos los campos como parámetros
@NoArgsConstructor // crea un constructor vacío
@Data // crea getters, setters, equals, hashCode y toString
@Entity // indica que es una entidad JPA
@Table(name = "cliente") // nombre exacto de la tabla en BD
public class ClienteEntity implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Id // clave primaria
    @Column(name = "id_cliente", nullable = false) // nombre de la columna en BD
    @GeneratedValue(strategy = GenerationType.IDENTITY) // hace que el Id sea auto incrementable
    private Integer id;

    @Column(name = "tipo_documento", nullable = false, length = 20)
    private String tipoDocumento;

    @Column(name = "numero_documento", nullable = false, length = 20)
    private String numeroDocumento;

    @Column(name = "nombres", nullable = false, length = 100)
    private String nombres;

    @Column(name = "apellidos", nullable = false, length = 100)
    private String apellidos;

    @Column(name = "email", length = 150)
    private String email;

    @Column(name = "telefono", length = 15)
    private String telefono; //

    @Column(name = "estado", length = 20)
    private String estado;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro; // Cambia de LocalDateTime a Date

    @Column(name = "usuario_registro", length = 50)
    private String usuarioRegistro;

    // ========== RELACIÓN ONE-TO-MANY ==========
    // Un cliente tiene muchas cuentas
    // CascadeType.REMOVE: Solo elimina cuentas al eliminar cliente
    // orphanRemoval = true: Elimina cuentas si se desasocian del cliente
    // @ToString.Exclude: Evita bucles infinitos en toString() al serializar JSON
    /*
     * @ToString.Exclude
     * 
     * @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval =
     * true)
     * private List<CuentaEntity> cuentas = new ArrayList<>();
     */

    // MÉTODOS CON LÓGICA DE NEGOCIO (validaciones)

    // Obtener nombre completo del cliente
    public String getNombreCompleto() {
        return this.nombres + " " + this.apellidos;
    }

    // Validar formato de email
    public boolean isEmailValido() {
        if (this.email == null || this.email.isEmpty()) {
            return false;
        }
        return this.email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    // Validar que el documento no esté vacío
    public boolean isDocumentoValido() {
        return this.numeroDocumento != null && !this.numeroDocumento.isEmpty();
    }

    // Verificar si el cliente está activo
    public boolean isActivo() {
        return "ACTIVO".equalsIgnoreCase(this.estado);
    }
    /*
     * // Agregar una cuenta al cliente
     * public void agregarCuenta(CuentaEntity cuenta) {
     * cuenta.add(cuenta);
     * cuenta.setCliente(this);
     * }
     */

}
