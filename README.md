# Conflict Tracker API

## 📋 Descripción
API REST desarrollada con Spring Boot 3 para gestionar información sobre conflictos bélicos a nivel mundial.
Incluye un frontend con Thymeleaf para visualizar los datos y perfiles de configuración para desarrollo (H2) y producción (PostgreSQL).

## 🛠️ Tecnologías utilizadas
- Java 17
- Spring Boot 3.5.7
- Spring Data JPA (Hibernate)
- Spring Web
- Spring Validation
- Thymeleaf
- H2 Database (desarrollo)
- PostgreSQL (producción)
- Maven
- Git

## 🚀 Ejecución del proyecto

### Requisitos previos
- Java 17 o superior
- Maven 3.6 o superior
- (Opcional) PostgreSQL para perfil de producción

### Perfiles disponibles
La aplicación utiliza perfiles de Spring para cambiar entre entornos:

| Perfil | Base de datos | Uso |
|--------|---------------|-----|
| `dev` (por defecto) | H2 en memoria | Desarrollo |
| `prod` | PostgreSQL | Producción |

## 🌐 Acceso a la aplicación

Una vez ejecutada la aplicación, puedes acceder a:

| Recurso | URL |
|---------|-----|
| **API REST** | `http://localhost:8080/api/v1/conflicts` |
| **Interfaz Web (Thymeleaf)** | `http://localhost:8080/web/conflicts` |
| **Consola H2 (solo perfil dev)** | `http://localhost:8080/h2-console` |
| **Frontend testimonial** | `http://localhost:8080` |

### Credenciales H2 (perfil dev)
- **JDBC URL:** `jdbc:h2:mem:conflictdb`
- **Usuario:** `sa`
- **Contraseña:** *(vacío)*

## Endpoints principales

### Conflictos
- `GET /api/v1/conflicts` - Listar todos los conflictos
- `GET /api/v1/conflicts?status=ACTIVE` - Filtrar conflictos por estado (ACTIVE, FROZEN, ENDED)
- `GET /api/v1/conflicts/{id}` - Obtener un conflicto por ID
- `GET /api/v1/conflicts/countries/{code}/conflicts` - Obtener conflictos por código de país (ej: /api/v1/conflicts/countries/UKR/conflicts)
- `POST /api/v1/conflicts` - Crear un nuevo conflicto
- `PUT /api/v1/conflicts/{id}` - Actualizar un conflicto existente
- `DELETE /api/v1/conflicts/{id}` - Eliminar un conflicto

### Facciones
- `GET /api/v1/factions` - Listar todas las facciones
- `GET /api/v1/factions?conflictId={id}` - Filtrar facciones por conflicto
- `GET /api/v1/factions/{id}` - Obtener una facción por ID
- `POST /api/v1/factions` - Crear una nueva facción
- `PUT /api/v1/factions/{id}` - Actualizar una facción existente
- `DELETE /api/v1/factions/{id}` - Eliminar una facción

### Eventos
- `GET /api/v1/events` - Listar todos los eventos
- `GET /api/v1/events?conflictId={id}` - Filtrar eventos por conflicto
- `GET /api/v1/events/{id}` - Obtener un evento por ID
- `POST /api/v1/events` - Crear un nuevo evento
- `PUT /api/v1/events/{id}` - Actualizar un evento existente
- `DELETE /api/v1/events/{id}` - Eliminar un evento

### Países
- `GET /api/v1/countries` - Listar todos los países
- `GET /api/v1/countries/{id}` - Obtener un país por ID
- `GET /api/v1/countries/code/{code}` - Obtener un país por su código (ej: /api/v1/countries/code/UKR)
- `POST /api/v1/countries` - Crear un nuevo país
- `DELETE /api/v1/countries/{id}` - Eliminar un país

### Interfaz web (Thymeleaf)
- `GET /web/conflicts` - Vista HTML con listado de conflictos