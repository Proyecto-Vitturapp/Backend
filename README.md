## Instalación

1. Descarga o clona el repositorio del proyecto.
2. Abre una terminal y dirígete al directorio raíz del backend:

```bash
cd proyecto-vitturapp/backend/Backend-21208054ef2df47769af89e73f0dae2a835d099e

```

3. Ejecuta la descarga de dependencias y realiza la compilación del proyecto utilizando el Maven Wrapper provisto:

```bash
./mvnw clean install

```

## Ejecución

Para levantar el servidor de desarrollo local, utiliza el comando de arranque del plugin de Spring Boot:

```bash
./mvnw spring-boot:run

```

La API se iniciará en el puerto configurado por defecto `8082`. El punto de acceso base para consumir las rutas es `http://localhost:8082/api`.

## Seguridad y Autenticación

El sistema implementa dos capas independientes de control de acceso configuradas dentro de la cadena de filtros de seguridad corporativa:

* **Tokens JWT (JSON Web Tokens):** Emitidos al realizar una autenticación satisfactoria en el endpoint de login. Debes adjuntarlo en las solicitudes posteriores bajo el encabezado HTTP: `Authorization: Bearer <tu_token_jwt>`.
* **Filtro API Key:** Permite accesos de servicios autorizados enviando el token secreto directamente. Se puede enviar como un encabezado personalizado `X-API-Key: <clave_secreta>` o incrustado dentro de `Authorization: Bearer <clave_secreta>`.

## Endpoints de la API

### Autenticación

* `POST /api/login`: Endpoint público para validar credenciales y obtener el token de acceso junto al ID de usuario.

### Gestión de Usuarios

* `POST /api/usuarios`: Permite el autoregistro de nuevos usuarios en la plataforma (Acceso Público).
* `GET /api/usuarios/total`: Obtiene el recuento total de usuarios dados de alta (Acceso Público).
* `GET /api/usuarios`: Recupera la lista de todos los usuarios registrados en el sistema.
* `GET /api/usuarios/{id}`: Obtiene el perfil de un usuario específico por su ID.
* `PUT /api/usuarios/{id}`: Actualiza los campos informativos de un usuario existente.
* `DELETE /api/usuarios/{id}`: Elimina la cuenta de usuario del sistema.
* `GET /api/vehiculos/{matricula}/usuarios`: Lista los usuarios propietarios o asociados a una matrícula.

### Control de Vehículos

* `GET /api/vehiculos/total`: Obtiene el número total de vehículos registrados en el taller (Acceso Público).
* `GET /api/vehiculos/usuarioid/{usuarioId}`: Lista todos los vehículos pertenecientes a un ID de usuario específico (Roles: CLIENTE, MECANICO).
* `GET /api/vehicles`: Retorna el listado completo de los vehículos en la base de datos (Roles: CLIENTE, MECANICO).
* `GET /api/vehicles/{plate}`: Obtiene el detalle de un vehículo por su matrícula (Roles: CLIENTE, MECANICO).
* `POST /api/vehicles`: Registra un nuevo vehículo en el sistema (Roles: CLIENTE, MECANICO).
* `POST /api/usuarios/{usuarioId}/vehicles/{plate}`: Asocia un vehículo existente con un usuario mediante relación de propiedad (Roles: CLIENTE, MECANICO).
* `PUT /api/vehicles/{plate}`: Modifica los datos del coche asociado a la matrícula (Roles: CLIENTE, MECANICO).
* `DELETE /api/vehicles/{plate}`: Elimina el registro del vehículo (Roles: CLIENTE, MECANICO).

### Control de Revisiones Técnicas

* `GET /api/revisiones/total`: Retorna el total acumulado de revisiones (Acceso Público).
* `GET /api/revisiones`: Obtiene el historial completo de revisiones técnicas realizadas (Exclusivo Rol: MECANICO).
* `GET /api/revisiones/{id}`: Recupera los detalles de una revisión por su identificador (Exclusivo Rol: MECANICO).
* `GET /api/revisiones/vehiculo/{matricula}`: Lista todas las hojas de revisión asociadas a una matrícula (Exclusivo Rol: MECANICO).
* `POST /api/revisiones`: Registra una nueva orden de revisión en el taller (Exclusivo Rol: MECANICO).
* `PUT /api/revisiones/{id}`: Edita o corrige una revisión técnica existente (Exclusivo Rol: MECANICO).
* `DELETE /api/revisiones/{id}`: Remueve de forma definitiva una revisión del historial (Exclusivo Rol: MECANICO). phot

## Ejemplos de Peticiones y Respuestas JSON

### 1. Autenticación (`POST /api/login`)

**Cuerpo de la Solicitud (Request Body):**

```json
{
  "username": "mecanico01",
  "password": "mypassword123"
}

```

**Respuesta Exitosa (200 OK):**

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtZWNhbmljbzAxIiwicm9sZSI6MSwiaWF0IjoxNzE2OTU4MTI...",
  "userId": 5
}

```

### 2. Creación de Usuario (`POST /api/usuarios`)

**Cuerpo de la Solicitud (Request Body):**

```json
{
  "username": "carlos_perez",
  "password": "passwordSeguro987",
  "nombre": "Carlos",
  "apellido": "Pérez",
  "segundoApellido": "Martínez",
  "email": "carlos.perez@example.com",
  "telefono": "654321098"
}

```

### 3. Registro de Vehículo (`POST /api/vehicles`)

**Cuerpo de la Solicitud (Request Body):**

```json
{
  "matricula": "1234ABC",
  "marca": "Ford",
  "modelo": "Focus",
  "anyoFabricacion": "2018",
  "tipoVehiculo": "Turismo"
}

```

### 4. Alta de Revisión Técnica (`POST /api/revisiones`)

*Nota: Las propiedades de fecha requieren estrictamente el patrón estipulado `dd-MM-yyyy`.*

**Cuerpo de la Solicitud (Request Body):**

```json
{
  "fechaRevision": "29-05-2026",
  "kilometrajeActual": 85000,
  "diagnosticoResultado": "Cambio de filtros de aire, cambio de aceite de motor sintético y revisión del sistema de escape.",
  "fechaProximoMantenimiento": "29-05-2027",
  "importe": 145.80,
  "matricula": "1234ABC",
  "idUsuario": 5
}

```
