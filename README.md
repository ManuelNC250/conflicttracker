# Conflict Tracker API

## Descripción
API REST desarrollada con Spring Boot para gestionar información sobre conflictos bélicos a nivel mundial.

## Tecnologías
- Java 17
- Spring Boot 3
- Spring Data JPA
- H2 Database (desarrollo)
- PostgreSQL (producción)

## Instalación
1. Clonar repositorio
2. Ejecutar: `mvn spring-boot:run`
3. Acceder a: http://localhost:8080

## Configuración para PostgreSQL

La aplicación está preparada para conectarse a PostgreSQL. Para usarla:

1. **Descomenta** la configuración de PostgreSQL en `application.properties`
2. **Comenta** la configuración de H2
3. **Asegúrate** de tener PostgreSQL instalado y una base de datos llamada `conflicttracker`
4. **Actualiza** username y password según tu configuración

## Endpoints principales

### Conflictos
- GET `/api/v1/conflicts` - Listar todos
- GET `/api/v1/conflicts?status=ACTIVE` - Filtrar por estado
- GET `/api/v1/conflicts/{id}` - Obtener por ID
- POST `/api/v1/conflicts` - Crear nuevo
- PUT `/api/v1/conflicts/{id}` - Actualizar
- DELETE `/api/v1/conflicts/{id}` - Eliminar

### Ejemplos con curl
```bash
# Obtener todos los conflictos
curl -X GET http://localhost:8080/api/v1/conflicts

# Crear nuevo conflicto
curl -X POST http://localhost:8080/api/v1/conflicts \
  -H "Content-Type: application/json" \
  -d '{"name":"Test","startDate":"2024-01-01","status":"ACTIVE","description":"Test"}'