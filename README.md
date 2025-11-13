# KRATOS_USER-MANAGEMENT_BACKEND

## Microservicio de Gestión de Usuarios - RidECI

---

## Desarrolladores

* David Santiago Palacios Pinzón
* Juan Carlos Leal Cruz
* Juan Sebastian Puentes Julio
* Sebastian Albarracin Silva
* Ana Gabriela Fiquitiva Poveda

---

## Tabla de Contenidos

* [Descripción](#descripción)
* [Características](#características)
* [Tecnologías Utilizadas](#tecnologías-utilizadas)
* [Estrategia de Versionamiento y Branching](#estrategia-de-versionamiento-y-branching)
  * [Estrategia de Ramas (Git Flow)](#estrategia-de-ramas-git-flow)
  * [Convenciones de Nomenclatura](#convenciones-de-nomenclatura)
  * [Convenciones de Commits](#convenciones-de-commits)
* [Arquitectura del Proyecto](#arquitectura-del-proyecto)
  * [Estructura de Capas](#estructura-de-capas)
  * [Flujo de una Petición](#flujo-de-una-petición)
* [Arquitectura Limpia - Organización de Capas](#arquitectura-limpia---organización-de-capas)
* [Diagramas del Módulo](#diagramas-del-módulo)
* [Instalación](#instalación)
* [Configuración](#configuración)
* [Uso](#uso)
* [API Endpoints](#api-endpoints)
* [Testing](#testing)
* [Despliegue](#despliegue)

---

## Descripción

Microservicio encargado de la **gestión completa de usuarios y sus perfiles** en la plataforma **RidECI**. Administra la información personal, roles, tipos de movilidad, verificación de documentos, reputación y el ciclo de vida completo de los usuarios dentro del sistema.

---

## Características

### Gestión de Perfiles de Usuario

**Creación y Administración:**
- Creación y actualización de perfiles de usuario
- Gestión de información personal (nombre, teléfono, dirección, foto de perfil)
- Administración de perfiles según tipo: Estudiante, Profesor, Empleado Administrativo
- Eliminación lógica de cuentas de usuario
- Consulta y búsqueda de usuarios por diferentes criterios

**Roles y Tipos de Movilidad:**
- Asignación y modificación de roles (estudiante, profesor, administrativo)
- Gestión de tipos de movilidad dinámicos (conductor, pasajero, acompañante)
- Configuración de permisos según tipo de usuario
- Cambio de roles con validación de permisos

### Verificación de Conductores

**Gestión de Documentos:**
- Registro y validación de licencia de conducción
- Verificación de información del vehículo (marca, modelo, color, placa)
- Validación de documentos de seguro vehicular (SOAT)
- Almacenamiento seguro de documentos digitalizados
- Sistema de aprobación/rechazo de documentos por administrador
- Notificaciones sobre estado de verificación

**Información Vehicular:**
- Registro de múltiples vehículos por conductor
- Actualización de datos del vehículo
- Gestión de placas visibles en la aplicación
- Historial de vehículos registrados

### Sistema de Reputación

**Calificaciones:**
- Registro de calificaciones recibidas (escala 1-5)
- Cálculo de promedio de reputación por usuario
- Gestión de comentarios y observaciones
- Historial completo de calificaciones

**Distintivos y Reconocimientos:**
- Asignación automática de distintivos ("Conductor confiable", "Pasajero frecuente")
- Sistema de logros según comportamiento
- Visualización de reconocimientos en perfil
- Estadísticas de reputación detalladas

### Administración de Usuarios

**Control Administrativo:**
- Aprobación de nuevos registros de usuarios
- Validación de conductores antes de habilitarlos
- Suspensión temporal o permanente de cuentas
- Bloqueo de usuarios por incumplimiento de políticas
- Reactivación de cuentas suspendidas
- Gestión de reportes de comportamiento

**Monitoreo y Auditoría:**
- Registro de actividad de usuarios (última conexión, acciones realizadas)
- Historial de cambios en perfiles
- Seguimiento de modificaciones administrativas
- Logs de acciones críticas
- Reportes de usuarios activos/inactivos

### Integración con Otros Microservicios

**Comunicación:**
- Validación de credenciales para el microservicio de autenticación
- Provisión de información de usuario para generación de tokens JWT
- Consulta de datos de usuario para módulo de viajes
- Sincronización de reputación con módulo de calificaciones
- Actualización de estadísticas con módulo de sostenibilidad

---

## Tecnologías Utilizadas

| **Categoría**              | **Tecnologías**                                          |
| -------------------------- | -------------------------------------------------------- |
| **Backend**                | Java 17, Spring Boot 3.5.7, Spring Data, Maven           |
| **Base de Datos**          | MongoDB, PostgreSQL (Opcional)                           |
| **Almacenamiento**         | AWS S3 / Azure Blob Storage (documentos e imágenes)      |
| **Validación**             | Bean Validation, Custom Validators                       |
| **Testing**                | JUnit 5, Mockito, Testcontainers, Jacoco, SonarQube     |
| **Documentación**          | Swagger UI, Postman                                      |
| **DevOps y Deploy**        | Docker, Kubernetes (K8s), GitHub Actions, Azure, Vercel  |
| **Comunicación**           | REST API, Event-Driven Architecture (opcional)           |
| **Gestión y Colaboración** | Git/GitHub, Figma, Slack, Jira                           |

---

## Estrategia de Versionamiento y Branching

Se implementa una estrategia de versionamiento basada en **GitFlow**, garantizando un flujo de desarrollo **colaborativo, trazable y controlado**.

### Beneficios:

- Permite trabajo paralelo sin conflictos
- Mantiene versiones estables y controladas
- Facilita correcciones urgentes (*hotfixes*)
- Proporciona un historial limpio y entendible

---

## Estrategia de Ramas (Git Flow)

| **Rama**                | **Propósito**                            | **Recibe de**           | **Envía a**        | **Notas**                      |
| ----------------------- | ---------------------------------------- | ----------------------- | ------------------ | ------------------------------ |
| `main`                  | Código estable para PREPROD o Producción | `release/*`, `hotfix/*` | Despliegue         | Protegida con PR y CI exitoso  |
| `develop`               | Rama principal de desarrollo             | `feature/*`             | `release/*`        | Base para integración continua |
| `feature/*`             | Nuevas funcionalidades o refactors       | `develop`               | `develop`          | Se eliminan tras el merge      |
| `release/*`             | Preparación de versiones estables        | `develop`               | `main` y `develop` | Incluye pruebas finales        |
| `bugfix/*` o `hotfix/*` | Corrección de errores críticos           | `main`                  | `main` y `develop` | Parches urgentes               |

---

## Convenciones de Nomenclatura

### Feature Branches

```
feature/[nombre-funcionalidad]-kratos-um_[codigo-jira]
```

**Ejemplos:**

```
- feature/driver-verification-kratos-um_23
- feature/reputation-system-kratos-um_35
```

**Reglas:**

* Formato: *kebab-case*
* Incluir código Jira
* Descripción breve y clara
* Longitud máxima: 50 caracteres

---

### Release Branches

```
release/[version]
```

**Ejemplos:**

```
- release/1.0.0
- release/1.1.0-beta
```

---

### Hotfix Branches

```
hotfix/[descripcion-breve-del-fix]
```

**Ejemplos:**

```
- hotfix/fix-profile-update
- hotfix/document-validation-patch
```

---

## Convenciones de Commits

### Formato Estándar

```
[codigo-jira] [tipo]: [descripción breve de la acción]
```

**Ejemplos:**

```
23-feat: implementar verificación de documentos de conductor
35-fix: corregir cálculo de promedio de reputación
```

---

### Tipos de Commit

| **Tipo**   | **Descripción**                      | **Ejemplo**                                           |
| ----------- | ------------------------------------ | ----------------------------------------------------- |
| `feat`      | Nueva funcionalidad                  | `23-feat: agregar endpoint de registro de vehículo`   |
| `fix`       | Corrección de errores                | `35-fix: solucionar error en actualización de perfil` |
| `docs`      | Cambios en documentación             | `41-docs: actualizar documentación de API`            |
| `refactor`  | Refactorización sin cambio funcional | `47-refactor: optimizar consulta de usuarios`         |
| `test`      | Pruebas unitarias o de integración   | `53-test: agregar tests para servicio de reputación`  |
| `chore`     | Mantenimiento o configuración        | `59-chore: actualizar dependencias de Spring`         |

**Reglas:**

* Un commit = una acción completa
* Máximo **72 caracteres** por línea
* Usar modo imperativo ("agregar", "corregir", etc.)
* Descripción clara de qué y dónde
* Commits pequeños y frecuentes

---

## Arquitectura del Proyecto

El backend de **KRATOS_USER-MANAGEMENT** sigue una **arquitectura limpia y desacoplada**, priorizando:

* Separación de responsabilidades
* Mantenibilidad
* Escalabilidad
* Facilidad de pruebas

---

## Estructura de Capas

```
📂 kratos_user_management_backend
 ┣ 📂 domain/
 ┃ ┣ 📄 Entities/
 ┃ ┣ 📄 ValueObjects/
 ┃ ┣ 📄 Enums/
 ┃ ┣ 📄 Services/
 ┃ ┗ 📄 Events/
 ┣ 📂 application/
 ┃ ┣ 📄 UseCases/
 ┃ ┣ 📄 DTOs/
 ┃ ┣ 📄 Mappers/
 ┃ ┗ 📄 Exceptions/
 ┣ 📂 infrastructure/
 ┃ ┣ 📄 API/Controllers/
 ┃ ┣ 📄 Database/
 ┃ ┣ 📄 Repositories/
 ┃ ┣ 📄 Config/
 ┃ ┣ 📄 Security/
 ┃ ┣ 📄 ExternalServices/
 ┃ ┗ 📄 ExceptionHandlers/
 ┗ 📄 pom.xml
```
---

## Flujo de una Petición

```
1. Cliente envía petición HTTP (ej: actualizar perfil)
   ↓
2. Controller (Infrastructure) - Recibe y valida el request
   ↓
3. Use Case (Application) - UpdateUserProfile orquesta la lógica
   ↓
4. Domain Service - Aplica reglas de negocio (validaciones)
   ↓
5. Repository Interface (Domain) - Contrato de persistencia
   ↓
6. Repository Implementation (Infrastructure) - Actualiza en MongoDB
   ↓
7. Event (Domain) - Se emite "UserProfileUpdated"
   ↓
8. Response fluye de vuelta transformándose en cada capa
```

---

## Arquitectura Limpia - Organización de Capas

### DOMAIN (Dominio)

**Propósito:** Representa el **núcleo del negocio** y contiene los conceptos más importantes de la aplicación. Define **QUÉ** hace el sistema, no **CÓMO** lo hace.

**Contiene:**

- **Entities:** User, Profile, Vehicle, Document, Reputation - objetos principales con sus reglas de negocio
- **Value Objects:** Email, PhoneNumber, LicensePlate - objetos inmutables con validaciones específicas
- **Enums:** UserRole (estudiante, profesor, administrativo), MobilityType (conductor, pasajero, acompañante), DocumentType, UserStatus
- **Repositories (interfaces):** Contratos para acceso a datos sin implementación técnica
- **Services:** Lógica compleja del dominio (cálculo de reputación, validación de documentos)
- **Events:** UserCreated, ProfileUpdated, DriverVerified, UserSuspended

**Principio clave:** Esta capa NO debe depender de frameworks, bases de datos o tecnologías externas.

---

### APPLICATION (Aplicación)

**Propósito:** Encapsula la **lógica de aplicación** y define los **casos de uso** del sistema. Orquesta cómo se utiliza el dominio para resolver problemas específicos.

**Contiene:**

- **Use Cases:** 
  - CreateUserProfile: Crea un nuevo perfil de usuario
  - UpdateUserProfile: Actualiza información personal
  - VerifyDriverDocuments: Valida documentos de conductor
  - CalculateReputation: Calcula y actualiza reputación
  - SuspendUser: Suspende un usuario
  - AssignMobilityType: Asigna tipos de movilidad
- **DTOs:** CreateUserRequest, UpdateProfileRequest, UserResponse, DriverVerificationResponse
- **Mappers:** Conversión entre entidades del dominio y DTOs
- **Exceptions:** UserNotFoundException, InvalidDocumentException, UnauthorizedException

**Ventaja principal:** Los casos de uso son independientes del framework y pueden ser reutilizados.

---

### INFRASTRUCTURE (Infraestructura)

**Propósito:** Implementa los **detalles técnicos** que permiten que el sistema funcione. Maneja persistencia, almacenamiento de archivos, comunicación externa y configuración.

**Contiene:**

- **API/Controllers:** Endpoints REST para gestión de usuarios, perfiles, conductores, reputación
- **Database:** Configuración de MongoDB, modelos con anotaciones específicas
- **Repositories:** Implementaciones usando Spring Data MongoDB
- **Config:** Configuración de CORS, beans, propiedades de aplicación
- **Storage:** Integración con AWS S3 o Azure Blob Storage para documentos/imágenes
- **External Services:** Comunicación con microservicio de autenticación, envío de emails
- **Exception Handlers:** Manejo centralizado de errores y respuestas HTTP

**Característica:** Esta capa SÍ depende de frameworks y tecnologías (Spring Boot, MongoDB, AWS S3, etc.).

---

### Beneficios de esta Arquitectura

| Característica                      | Beneficio                                                          |
| ----------------------------------- | ------------------------------------------------------------------ |
| **Independencia de Frameworks**     | El dominio no depende de Spring, MongoDB o cualquier tecnología    |
| **Testabilidad**                    | Cada capa puede probarse de forma aislada con mocks                |
| **Mantenibilidad**                  | Cambios en BD o framework no afectan la lógica de negocio          |
| **Escalabilidad**                   | Fácil agregar nuevos casos de uso sin modificar código existente   |
| **Claridad**                        | Responsabilidades bien definidas facilitan comprensión del código  |

---

## Diagramas del Módulo

### Diagrama de Contexto

![Diagrama de Contexto](docs/uml/diagrama_contexto.png)

**Explicación:**

*[Pendiente de documentación]*

---

### Diagrama de Clases

![Diagrama de Clases](docs/uml/diagrama_clases.png)

**Explicación:**

*[Pendiente de documentación]*

---

### Diagrama de Componentes Específico

![Diagrama de Componentes](docs/images/diagrama_componentes.png)

**Explicación:**

*[Pendiente de documentación]*

---

### Diagrama de Despliegue

![Diagrama de Despliegue](docs/uml/diagrama_despliegue.png)

**Explicación:**

*[Pendiente de documentación]*

---

### Diagrama de Bases de Datos

![Diagrama de Bases de Datos](docs/uml/diagrama_bd.png)

**Explicación:**

*[Pendiente de documentación]*

---

## Instalación

### Prerrequisitos

- Java 17
- Maven
- MongoDB 
- Git

### Clonar el repositorio

```bash
git clone https://github.com/RIDECI/KRATOS_USER-MANAGEMENT_BACKEND.git
cd KRATOS_USER-MANAGEMENT_BACKEND
```

### Instalar dependencias

```bash
mvn clean install
```

---

## Configuración

*[Sección pendiente de completar con variables de entorno, configuración de base de datos, AWS S3, etc.]*

---

## Uso

*[Sección pendiente de completar con instrucciones de ejecución local, desarrollo, etc.]*

---

## API Endpoints

*[Sección pendiente de completar con documentación de endpoints principales]*

### Gestión de Usuarios
- `POST /api/users` - Crear perfil de usuario
- `GET /api/users/{id}` - Obtener usuario por ID
- `PUT /api/users/{id}` - Actualizar perfil de usuario
- `DELETE /api/users/{id}` - Eliminar usuario (lógico)
- `GET /api/users` - Listar usuarios con filtros

### Gestión de Conductores
- `POST /api/drivers/verify` - Enviar documentos para verificación
- `PUT /api/drivers/{id}/documents` - Actualizar documentos
- `POST /api/drivers/{id}/vehicles` - Registrar vehículo
- `GET /api/drivers/{id}/vehicles` - Listar vehículos del conductor

### Sistema de Reputación
- `POST /api/reputation/rating` - Registrar calificación
- `GET /api/reputation/user/{id}` - Obtener reputación de usuario
- `GET /api/reputation/user/{id}/history` - Historial de calificaciones

### Administración
- `PUT /api/admin/users/{id}/suspend` - Suspender usuario
- `PUT /api/admin/users/{id}/activate` - Activar usuario
- `PUT /api/admin/drivers/{id}/approve` - Aprobar conductor
- `PUT /api/admin/drivers/{id}/reject` - Rechazar conductor

---

## Testing

*[Sección pendiente de completar con instrucciones de ejecución de tests, cobertura, etc.]*

---

## Despliegue

*[Sección pendiente de completar con instrucciones de despliegue en Azure, configuración de K8s, etc.]*

---

**RidECI** - Conectando a la comunidad para moverse de forma segura, económica y sostenible.
