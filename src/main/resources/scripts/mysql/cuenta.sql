USE db_jpa_crud;
SHOW TABLES;

/* =========================================================
   3. TABLA CUENTA
   - Relacionada a cliente
   - Usa catálogo para tipo de cuenta
   - Maneja saldo, moneda y estado
   ========================================================= */
CREATE TABLE cuenta (
    id_cuenta BIGINT PRIMARY KEY AUTO_INCREMENT,
    id_cliente BIGINT NOT NULL,                     -- FK a cliente
    numero_cuenta VARCHAR(30) UNIQUE NOT NULL,
    tipo_cuenta VARCHAR(20),                        -- FK a catálogo
    saldo DECIMAL(15,2) DEFAULT 0,
    moneda VARCHAR(5),                              -- PEN / USD
    estado VARCHAR(20),                             -- ACTIVA / INACTIVA
    fecha_apertura DATE,
    CONSTRAINT fk_cuenta_cliente
        FOREIGN KEY (id_cliente)
        REFERENCES cliente(id_cliente)
        
);
-- Cuentas asociadas a clientes
INSERT INTO cuenta (
    id_cliente,
    numero_cuenta,
    tipo_cuenta,
    saldo,
    moneda,
    estado,
    fecha_apertura
) VALUES
(1, '001-000123456789', 'AHO', 1500.00, 'PEN', 'ACTIVA', CURRENT_DATE),
(2, '001-000987654321', 'CTE', 3500.50, 'USD', 'ACTIVA', CURRENT_DATE);

-- verificar la tabla 
DESCRIBE cuenta;

-- Consulta cruda de cuentas
-- Solo para debugging o validación técnica
SELECT
    id_cuenta,         -- PK interna
    id_cliente,        -- FK al cliente
    numero_cuenta,     -- Número único de cuenta
    tipo_cuenta,       -- Código del catálogo
    saldo,             -- Saldo actual
    moneda,            -- PEN / USD
    estado,            -- ACTIVA / BLOQUEADA
    fecha_apertura     -- Fecha de apertura
FROM cuenta;