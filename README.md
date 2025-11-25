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
* [Arquitectura Limpia - Organización de Capas](#arquitectura-limpia---organización-de-capas)
* [Diagramas del Módulo](#diagramas-del-módulo)
* [Instalación](#instalación)
* [API Endpoints](#api-endpoints)

---

## Descripción

Microservicio encargado de la **gestión completa de usuarios y sus perfiles** en la plataforma **RidECI**. Administra la información personal, roles, tipos de movilidad, verificación de documentos, reputación y el ciclo de vida completo de los usuarios dentro del sistema.

---

## Características

### Gestión de Perfiles de Usuario

**Creación y Administración:**
- Creación y actualización de perfiles de usuario
- Gestión de información personal (nombre, teléfono, dirección)
- Administración de perfiles según tipo: Estudiante, Profesor, Administrativo
- Eliminación lógica de cuentas de usuario
- Consulta y búsqueda de usuarios por diferentes criterios

**Roles:**
- Asignación y modificación de roles (estudiante, profesor, administrativo)

### Integración con Otros Microservicios

**Comunicación:**
- Validación de credenciales para el microservicio de autenticación
- Provisión de información de usuario para generación de tokens JWT
- Sincronización de reputación con módulo de calificaciones
- Actualización de estadísticas con módulo de sostenibilidad

---

## Tecnologías Utilizadas

| **Categoría**              | **Tecnologías**                                          |
| -------------------------- | -------------------------------------------------------- |
| **Backend**                | Java 17, Spring Boot 3.5.7, Spring Data, Maven           |
| **Base de Datos**          | MongoDB                          |                   |
| **Testing**                | JUnit 5, Mockito, Jacoco, SonarQube     |
| **Documentación**          | Swagger UI, Postman                                      |
| **DevOps y Deploy**        | Docker, Kubernetes (K8s), GitHub Actions, Railway |
| **Comunicación**           | REST API, Event-Driven Architecture con RabbitMQ           |
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

### Develop Branches

```
feature/[nombre-funcionalidad]

```

**Ejemplos:**

```
- feature/driver-verification-kratos-um_23
- feature/reputation-system-kratos-um_35

```

**Reglas:**

* Descripción breve y clara

---

## Convenciones de Commits

### Formato Estándar

```
[tipo]: [descripción breve de la acción]
```

**Ejemplos:**

```
feat: implementar verificación de documentos de conductor
fix: corregir cálculo de promedio de reputación
```

---

### Tipos de Commit

| **Tipo**   | **Descripción**                      | **Ejemplo**                                           |
| ----------- | ------------------------------------ | ----------------------------------------------------- |
| `feat`      | Nueva funcionalidad                  | `feat: agregar endpoint de registro de vehículo`   |
| `fix`       | Corrección de errores                | `fix: solucionar error en actualización de perfil` |
| `docs`      | Cambios en documentación             | `docs: actualizar documentación de API`            |
| `refactor`  | Refactorización sin cambio funcional | `refactor: optimizar consulta de usuarios`         |
| `test`      | Pruebas unitarias o de integración   | `test: agregar tests para servicio de reputación`  |
| `chore`     | Mantenimiento o configuración        | `chore: actualizar dependencias de Spring`         |

**Reglas:**

* Un commit = una acción completa
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
 ┃
 ┣ 📂 application/
 ┃  ┣ 📂 events/
 ┃  ┃  ┣ 📂 listener/
 ┃  ┃  ┣ 📄 Event
 ┃  ┣ 📂 mapper/
 ┃  ┣ 📂 port/
 ┃  ┃  ┣ 📂 in/
 ┃  ┃  ┣ 📂 out/
 ┃  ┣ 📂 service/
 ┃
 ┣ 📂 domain/
 ┃  ┣ 📂 model/
 ┃     ┣ 📂 enums/
 ┃     ┣ 📄 Modelo De Negocio
 ┃
 ┣ 📂 infrastructure/
 ┃  ┣ 📂 config/
 ┃  ┣ 📂 controller/
 ┃  ┃  ┣ 📂 dto/
 ┃  ┃   ┃  ┣ 📂 request/
 ┃  ┃   ┃  ┣ 📂 response/
 ┃  ┃   ┣ 📄 Controller
 ┃  ┃
 ┃  ┣ 📂 persistance/
 ┃     ┣ 📂 entity/
 ┃     ┣ 📂 repository/
 ┃        ┣ 📂 mapper/
 ┃
 ┗ 📄 pom.xml
```
---

## Arquitectura Limpia - Organización de Capas

### DOMAIN (Dominio)

**Propósito:** Representa el **núcleo del negocio** y contiene los conceptos más importantes de la aplicación. Define **QUÉ** hace el sistema, no **CÓMO** lo hace.

**Contiene:**

- **Entities:** User.
- **Enums:** Role (STUDENT, PROFFESOR, ADMINISTRATOR), AccountState (ACTIVE, INACTIVE, PENDING, SUSPENDED), IdentificationType (TI, CC, PP, CE).

**Principio clave:** Esta capa NO debe depender de frameworks, bases de datos o tecnologías externas.

---

### APPLICATION (Aplicación)

**Propósito:** Encapsula la **lógica de aplicación** y define los **casos de uso** del sistema. Orquesta cómo se utiliza el dominio para resolver problemas específicos.

**Contiene:**

- **Use Cases:**
  - CreateUserUseCase: Crea un nuevo usuario
  - UpdateUserUseCase: Actualiza información personal
  - DeleteUserUseCase: Eliminar Usuario
  - GetUserUseCase: Obtener usuario por id
  - GetAllUsersUseCase: Obtener todos los usuarios
- **OUTs:** EventPublisher, UserRepositoryOutPort
- **LISTENERs:** UserRegisteredListener
- **Mapper:** UserMapperApplication
- **Service:** UserService

---

### INFRASTRUCTURE (Infraestructura)

**Propósito:** Implementa los **detalles técnicos** que permiten que el sistema funcione. Maneja persistencia, almacenamiento de archivos, comunicación externa y configuración.

**Contiene:**
- **config:** Configuración RabbitConfig
- **API/Controllers:** Endpoints REST para gestión de usuarios.
- **DTOs:** UserRequest, UserResponse
- **Repositories:** Implementaciones usando Spring Data MongoDB (UserRepository)
- **External Services:** RabbitEventPublisher
**Característica:** Esta capa SÍ depende de frameworks y tecnologías (Spring Boot, MongoDB, Railway, etc.).

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

![Diagrama de Componentes](docs/uml/diagrama_componentes.png)

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

## Uso

```bash
mvn clean install
```
Para ejecutar el proyecto:

```bash
mvn spring-boot:run
```
Ó:

```bash
docker compose up -d
```

---

## API Endpoints

### Gestión de Usuarios
- `POST /users` - Crear perfil de usuario
- `GET /users/{id}` - Obtener usuario por ID
- `PUT /users/{id}` - Actualizar perfil de usuario
- `DELETE /users/{id}` - Eliminar usuario (lógico)
- `GET /users/allUsers` - Listar usuarios con filtros

---
Pruebas Unitarias

![img.png](docs/img.png)

---

Reporte Jacoco

![img.png](docs/img2.png)
**RidECI** - Conectando a la comunidad para moverse de forma segura, económica y sostenible.
