# GUÍA POSTMAN - PRUEBAS DE ENDPOINTS

---

## ANTES DE EMPEZAR

### Requisitos:
1. **Postman instalado** (https://www.postman.com/downloads/)
2. **Spring Boot corriendo** en `http://localhost:8081`
3. **Base de datos MySQL activa**

### Estructura de URLs:
```
BASE URL: http://localhost:8081
Clientes: http://localhost:8081/api/clientes/todos
Cuentas:  http://localhost:8081/api/cuentas
```

---

## 📋 ENDPOINTS DISPONIBLES

### **CLIENTES**

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/clientes/todos` | Obtener todos los clientes |
| GET | `/api/clientes/{id}` | Obtener cliente por ID |
| POST | `/api/clientes` | Crear nuevo cliente |
| PUT | `/api/clientes/{id}` | Actualizar cliente |
| DELETE | `/api/clientes/{id}` | Eliminar cliente |

### **CUENTAS**

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/cuentas` | Obtener todas las cuentas |
| GET | `/api/cuentas/{id}` | Obtener cuenta por ID |
| POST | `/api/cuentas` | Crear nueva cuenta |
| PUT | `/api/cuentas/{id}` | Actualizar cuenta |
| DELETE | `/api/cuentas/{id}` | Eliminar cuenta |

---

## 1️⃣ PRUEBAS CON CLIENTES

### **TEST 1: Crear un cliente (POST)**

**URL:** `POST http://localhost:8081/api/clientes`

**Headers:**
```
Content-Type: application/json
```

**Body (JSON):**
```json
{
  "tipoDocumento": "DNI",
  "numeroDocumento": "21345678",
  "nombres": "Max",
  "apellidos": "Vasquez",
  "email": "max.vasquez@gmail.com",
  "telefono": "967279561",
  "estado": "ACTIVO"
}
```

**Respuesta esperada (201 Created):**
```json
{
  "id": 1,
  "tipoDocumento": "DNI",
  "numeroDocumento": "12345678",
  "nombres": "Juan",
  "apellidos": "Pérez",
  "email": "juan.perez@gmail.com",
  "telefono": "987654321",
  "estado": "ACTIVO",
  "fechaRegistro": "2026-01-21T14:30:00",
  "usuarioRegistro": "SISTEMA"
}
```

---

### **TEST 2: Crear segundo cliente (POST)**

**URL:** `POST http://localhost:8081/api/clientes`

**Body:**
```json
{
  "tipoDocumento": "PASAPORTE",
  "numeroDocumento": "ABC198456",
  "nombres": "Alisson",
  "apellidos": "Baldeon",
  "email": "alisson.baldeon@gmail.com",
  "telefono": "987654452",
  "estado": "ACTIVO"
}
```

---

### **TEST 3: Obtener todos los clientes (GET)**

**URL:** `GET http://localhost:8081/api/clientes/todos`

**Respuesta esperada:**
```json
[
  {
    "id": 1,
    "nombres": "Juan",
    "apellidos": "Pérez",
    "email": "juan.perez@gmail.com",
    ...
  },
  {
    "id": 2,
    "nombres": "María",
    "apellidos": "García",
    "email": "maria.garcia@gmail.com",
    ...
  }
]
```

---

### **TEST 4: Obtener cliente por ID (GET)**

**URL:** `GET http://localhost:8081/api/clientes/1`

**Respuesta esperada:**
```json
{
  "id": 1,
  "tipoDocumento": "DNI",
  "numeroDocumento": "12345678",
  "nombres": "Juan",
  "apellidos": "Pérez",
  "email": "juan.perez@gmail.com",
  "telefono": "987654321",
  "estado": "ACTIVO",
  "fechaRegistro": "2026-01-21T14:30:00",
  "usuarioRegistro": "SISTEMA"
}
```

---

### **TEST 5: Actualizar cliente (PUT)**

**URL:** `PUT http://localhost:8081/api/clientes/1`

**Body:**
```json
{
  "tipoDocumento": "DNI",
  "numeroDocumento": "12345678",
  "nombres": "Juan José",
  "apellidos": "Pérez López",
  "email": "juanjose.perez@gmail.com",
  "telefono": "987654325",
  "estado": "ACTIVO",
  "fechaRegistro": "2026-01-21T14:30:00",
  "usuarioRegistro": "SISTEMA"
}
```

---

### **TEST 6: Eliminar cliente (DELETE)**

**URL:** `DELETE http://localhost:8081/api/clientes/1`

**Respuesta esperada:** 200 OK (sin contenido)

---

## 2️⃣ PRUEBAS CON CUENTAS

### **IMPORTANTE: Crear clientes PRIMERO**

Las cuentas necesitan un cliente existente. Asegúrate de tener creados clientes con ID 1 y 2.

---

### **TEST 1: Crear una cuenta (POST)**

**URL:** `POST http://localhost:8081/api/cuentas`

**Headers:**
```
Content-Type: application/json
```

**Body (JSON):**
```json
{
  "cliente": {
    "id": 1
  },
  "numeroCuenta": "1234567890123",
  "tipoCuenta": "AHO",
  "saldo": 1000.00,
  "moneda": "USD",
  "estado": "ACTIVA"
}
```

**Respuesta esperada (201 Created):**
```json
{
  "id": 1,
  "cliente": {
    "id": 1,
    "nombres": "Juan",
    "apellidos": "Pérez",
    ...
  },
  "numeroCuenta": "1234567890123",
  "tipoCuenta": "AHORROS",
  "saldo": 1000.00,
  "moneda": "USD",
  "estado": "ACTIVA",
  "fechaApertura": "2026-01-21T14:35:00"
}
```

---

### **TEST 2: Crear segunda cuenta (POST)**

**URL:** `POST http://localhost:8081/api/cuentas`

**Body:**
```json
{
  "cliente": {
    "id": 2
  },
  "numeroCuenta": "9876543210987",
  "tipoCuenta": "CORRIENTE",
  "saldo": 5000.00,
  "moneda": "USD",
  "estado": "ACTIVA"
}
```

---

### **TEST 3: Obtener todas las cuentas (GET)**

**URL:** `GET http://localhost:8081/api/cuentas`

**Respuesta esperada:**
```json
[
  {
    "id": 1,
    "cliente": { "id": 1, "nombres": "Juan", ... },
    "numeroCuenta": "1234567890123",
    "tipoCuenta": "AHORROS",
    "saldo": 1000.00,
    ...
  },
  {
    "id": 2,
    "cliente": { "id": 2, "nombres": "María", ... },
    "numeroCuenta": "9876543210987",
    "tipoCuenta": "CORRIENTE",
    "saldo": 5000.00,
    ...
  }
]
```

---

### **TEST 4: Obtener cuenta por ID (GET)**

**URL:** `GET http://localhost:8081/api/cuentas/1`

**Respuesta esperada:**
```json
{
  "id": 1,
  "cliente": {
    "id": 1,
    "tipoDocumento": "DNI",
    "numeroDocumento": "12345678",
    "nombres": "Juan",
    "apellidos": "Pérez",
    ...
  },
  "numeroCuenta": "1234567890123",
  "tipoCuenta": "AHORROS",
  "saldo": 1000.00,
  "moneda": "USD",
  "estado": "ACTIVA",
  "fechaApertura": "2026-01-21T14:35:00"
}
```

---

### **TEST 5: Actualizar cuenta (PUT)**

**URL:** `PUT http://localhost:8081/api/cuentas/1`

**Body:**
```json
{
  "cliente": {
    "id": 1
  },
  "numeroCuenta": "1234567890123",
  "tipoCuenta": "AHORROS",
  "saldo": 2500.50,
  "moneda": "USD",
  "estado": "ACTIVA",
  "fechaApertura": "2026-01-21T14:35:00"
}
```

---

### **TEST 6: Eliminar cuenta (DELETE)**

**URL:** `DELETE http://localhost:8081/api/cuentas/1`

**Respuesta esperada:** 200 OK (sin contenido)

---

## ERRORES COMUNES Y SOLUCIONES

### **Error 400 - Bad Request**
**Causa:** JSON malformado o falta de campos obligatorios

**Solución:**
- Verifica que el JSON sea válido (usa https://jsonlint.com/)
- Asegúrate de incluir todos los campos obligatorios
- Verifica los tipos de datos (número, texto, etc.)

---

### **Error 404 - Not Found**
**Causa:** El recurso no existe

**Solución:**
- Verifica el ID existe (obtén todos primero con GET)
- Verifica la URL esté correcta
- Verifica que estés usando el ID correcto

---

### **Error 409 - Conflict**
**Causa:** Número de documento o número de cuenta duplicado

**Solución:**
- Usa números únicos en cada prueba
- Elimina clientes/cuentas anteriores si es necesario

---

### **Error 500 - Internal Server Error**
**Causa:** Error en la validación del servicio

**Solución:**
- Verifica el email tenga formato válido: `usuario@dominio.com`
- Verifica el número de cuenta tenga mínimo 10 dígitos
- Verifica que el cliente existe antes de crear una cuenta

---

## ORDEN RECOMENDADO DE PRUEBAS

1.  **POST Cliente 1** - Crear primer usuario
2.  **POST Cliente 2** - Crear segundo usuario
3.  **GET Clientes** - Ver todos los clientes
4.  **GET Cliente/1** - Ver cliente específico
5.  **PUT Cliente/1** - Actualizar primer usuario
6.  **POST Cuenta 1** - Crear cuenta del primer cliente(usa cliente ID 1)
7.  **POST Cuenta 2** - Crear cuenta de segundo cliente(usa cliente ID 2)
8.  **GET Cuentas** - Ver todas las cuentas
9.  **GET Cuenta/1** - Ver cuenta específica
10. **PUT Cuenta/1** - Actualizar cuenta
11. **DELETE Cuenta/1** - Eliminar cuenta
12. **DELETE Cliente/1** - Eliminar cliente

---

## TIPS PARA POSTMAN

### **Guardar una colección:**
1. Click en "New Collection"
2. Dale un nombre: "Banco CRUD"
3. Agrega todas las pruebas
4. Guarda para reutilizar

### **Variables en Postman:**
```
Base URL: {{baseUrl}}/api/clientes
// En variables poner: baseUrl = http://localhost:8081
```

### **Automatizar pruebas:**
1. Click en "Tests" (pestaña)
2. Agrega scripts para validar respuestas
3. Ejecuta la colección

---

##  VALIDACIONES QUE HACE EL CÓDIGO

### **Cliente - Validaciones en save():**
-  Cliente no nulo
-  Número de documento obligatorio
-  Nombre obligatorio
-  Apellido obligatorio
-  Email válido (si se proporciona)
-  Asigna fecha de registro si es nuevo

### **Cuenta - Validaciones en save():**
-  Cuenta no nula
-  Cliente existe en BD
-  Número de cuenta válido (≥10 dígitos numéricos)
-  Saldo válido (≥ 0)
-  Asigna fecha de apertura si es nueva
-  Asigna estado "ACTIVA" si no tiene