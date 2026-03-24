package com.example.primer_crud.stefany.springboot.app.demo.entities;

//import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
//import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
//import lombok.ToString;
import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;

//import com.example.primer_crud.stefany.springboot.app.demo.entities.Otros.MovimientoCuentaEntity;

@Data
@Entity
@Table(name = "cuenta")
public class CuentaEntity {

    @Id
    @Column(name = "id_cuenta", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // Muchas cuentas pueden pertenecer a un cliente
    @JoinColumn(name = "id_cliente", nullable = false) // Clave foránea que referencia a Cliente
    // Cuando se elimina el cliente, la cascada desde ClienteEntity se encargará de
    // eliminar esta cuenta
    private ClienteEntity cliente; // Se guarda el OBJETO Cliente, no el id

    @Column(name = "numero_cuenta", nullable = false, length = 30, unique = true)
    private String numeroCuenta;
    @Column(name = "tipo_cuenta", length = 20)
    private String tipoCuenta;
    @Column(name = "saldo", nullable = false)
    private Double saldo;
    @Column(name = "moneda", length = 5)
    private String moneda;
    @Column(name = "estado", length = 20)
    private String estado;
    @Column(name = "fecha_apertura")
    private String fechaApertura;

    // ========== RELACIÓN ONE-TO-MANY ==========
    // Una cuenta tiene muchos movimientos
    // CascadeType.REMOVE: Cuando se elimina la cuenta, se eliminan TODOS sus
    // movimientos
    // orphanRemoval = true: Si un movimiento se desasocia de la cuenta, se elimina
    // @ToString.Exclude: Evita bucles infinitos en toString() al serializar JSON
    /*
     * @ToString.Exclude
     * 
     * @OneToMany(mappedBy = "cuenta", cascade = CascadeType.REMOVE, orphanRemoval =
     * true)
     * private List<MovimientoCuentaEntity> movimientos = new ArrayList<>();
     */

    // Constructor vacío - REQUERIDO por JPA
    public CuentaEntity() {
    }

    // Constructor con parámetros - para crear nuevas cuentas
    public CuentaEntity(ClienteEntity cliente, String numeroCuenta, String tipoCuenta,
            Double saldo, String moneda, String estado) {
        this.cliente = cliente;
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldo = saldo;
        this.moneda = moneda;
        this.estado = estado;
        this.fechaApertura = LocalDateTime.now().toString();
    }

    // MÉTODOS CON LÓGICA DE NEGOCIO

    // Validar que el número de cuenta tenga formato correcto
    public boolean isNumeroCuentaValido() {

        if (numeroCuenta == null || cliente == null) {
            return false;
        }

        // Caso 1: Cliente con DNI (nacional)
        if ("DNI".equalsIgnoreCase(cliente.getTipoDocumento())) {
            return numeroCuenta.matches("\\d{10,}");
        }

        // Caso 2: Cliente con PASAPORTE (extranjero)
        if ("PASAPORTE".equalsIgnoreCase(cliente.getTipoDocumento())) {
            return numeroCuenta.matches("[A-Z0-9]{8,}");
        }

        return false;
    }

    // Validar que el saldo sea positivo
    public boolean isSaldoValido() {
        return this.saldo != null && this.saldo >= 0;
    }

    // Verificar si la cuenta está activa
    public boolean isActiva() {
        return "ACTIVA".equalsIgnoreCase(this.estado);
    }

    // Depositar dinero a la cuenta
    public boolean depositar(Double monto) {
        if (monto != null && monto > 0) {
            this.saldo += monto;
            return true;
        }
        return false;
    }

    // Retirar dinero de la cuenta
    public boolean retirar(Double monto) {
        if (monto != null && monto > 0 && this.saldo >= monto) {
            this.saldo -= monto;
            return true;
        }
        return false;
    }

    // Obtener información resumida de la cuenta
    public String getResumenCuenta() {
        return "Cuenta: " + this.numeroCuenta +
                " | Tipo: " + this.tipoCuenta +
                " | Saldo: " + this.saldo + " " + this.moneda;
    }

}
