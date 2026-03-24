USE db_jpa_crud;
SHOW TABLES;

/* =========================================================
   2. TABLA CLIENTE
   - Representa al cliente del banco
   - No se elimina físicamente (se maneja por estado)
   ========================================================= */
CREATE TABLE cliente (
    id_cliente BIGINT PRIMARY KEY AUTO_INCREMENT,
    tipo_documento VARCHAR(10) NOT NULL, -- DNI, CE, PAS
    numero_documento VARCHAR(20) NOT NULL UNIQUE,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    telefono VARCHAR(20),
    estado VARCHAR(20) NOT NULL, -- ACTIVO / INACTIVO / BLOQUEADO
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    usuario_registro VARCHAR(50)
);

-- Clientes de ejemplo (datos realistas)
INSERT INTO cliente (
    tipo_documento,
    numero_documento,
    nombres,
    apellidos,
    email,
    telefono,
    estado,
    usuario_registro
) VALUES
('DNI', '74859632', 'Juan Carlos', 'Pérez Ramos', 'juan.perez@email.com', '987654321', 'ACTIVO', 'admin'),
('DNI', '70984521', 'María Elena', 'Gómez Torres', 'maria.gomez@email.com', '912345678', 'ACTIVO', 'admin');

-- verificar la tabla 
DESCRIBE cliente;


-- Consulta básica de clientes
-- Usada por mantenimiento o búsqueda interna
SELECT
    id_cliente,
    nombres,
    apellidos,
    tipo_documento,
    numero_documento,
    estado
FROM cliente;