# Alquiler de películas y series

### Descripción

Sistema de gestión de alquiler de contenido audiovisual desarrollado con **Spring Boot** que permite administrar películas, series, suscriptores y sus alquileres. 
El sistema implementa una arquitectura en capas con API REST, validaciones de negocio y persistencia en base de datos.

---

## Diagrama Entidad-Relación
erDiagram
    CATEGORIA ||--o{ PRODUCTO : tiene
    IDIOMA ||--o{ PRODUCTO : "está en"
    PRODUCTO }o--|| ALQUILER : "incluido en"
    SUSCRIPTOR ||--o{ ALQUILER : realiza

    CATEGORIA {
        int id PK
        string nombre
    }
    
    IDIOMA {
        int id PK
        string codigo
        string nombre
    }
    
    PRODUCTO {
        int id PK
        string titulo
        string tipo
    }
    
    ALQUILER {
        int id PK
        date fecha_inicio
        date fecha_fin
        int suscriptor_id FK
    }
    
    SUSCRIPTOR {
        int id PK
        string nombre
        string email
    }

---

## Endpoints PRINCIPALES de la API REST

### Base URL: `http://localhost:8080/api`

###  Categorías (`/api/categorias`)

| Método | Endpoint             |  Descripción    |
|--------|----------------------|-----------------|
| `GET` | `/categorias`         | Listar todas    |
| `GET` | `/categorias/{id}`    | Obtener por ID  |
| `POST` | `/categorias`        | Crear           |
| `DELETE` | `/categorias/{id}` | Eliminar        |

### Idiomas (`/api/idiomas`)

| Método | Endpoint           | Descripción    |
|-------|---------------------|----------------|
| `GET` | `/idiomas`          | Listar todos   |
| `GET` | `/idiomas/{id}`     | Obtener por ID |
| `POST` | `/idiomas`         | Crear          |
| `DELETE` | `/idiomas/{id}`  | Eliminar       |

### Suscriptores (`/api/suscriptores`)

| Método | Endpoint               | Descripción         |
|--------|------------------------|---------------------|
| `GET` | `/suscriptores`         | Listar todos        | 
| `GET` | `/suscriptores/{id}`    | Obtener por ID      |
| `POST` | `/suscriptores`        | Crear               |
| `PUT` | `/suscriptores/{id}`    | Actualizar          |
| `DELETE` | `/suscriptores/{id}` | Eliminar            |



### Productos (`/api/productos`)

| Método | Endpoint            | Descripción    | 
|--------|---------------------|----------------|
| `GET` | `/productos`         | Listar todos   |
| `GET` | `/productos/{id}`    | Obtener por ID |
| `POST` | `/productos`        | Crear          |
| `PUT` | `/productos/{id}`    | Actualizar     |
| `DELETE` | `/productos/{id}` | Eliminar       |


### Alquileres (`/api/alquileres`)

| Método | Endpoint             | Descripción    |
|--------|----------------------|----------------|
| `GET` | `/alquileres`         | Listar todos   |
| `GET` | `/alquileres/{id}`    | Obtener por ID |
| `POST` | `/alquileres`        | Crear          |
| `PUT` | `/alquileres/{id}`    | Actualizar     |
| `DELETE` | `/alquileres/{id}` | Eliminar       |


---

## Reglas de negocio principales

### 1. Cálculo automático de la fecha de vencimiento de un suscriptor

**Implementación en `Alquiler.java`:**

```java
public void calcularFechaFin() {
    if (this.suscriptor != null && this.fechaInicio != null) {
        int diasAlquiler = this.suscriptor.getPlanContratado() 
            == Suscriptor.PlanContratado.PREMIUM ? 28 : 14;
        this.fechaFin = this.fechaInicio.plusDays(diasAlquiler);
    }
}
```

| Plan    | Duración del Alquiler |
|---------|-----------------------|
| BÁSICO  | 14 días (2 semanas)   |
| PREMIUM | 28 días (4 semanas)   |

### 2. Validaciones de Negocio

- Un producto debe tener AL MENOS una categoría
- Un producto debe tener AL MENOS un idioma
- NO se permiten categorías duplicadas
- NO se permiten correos electrónicos duplicados
- NO se permite alquilar el mismo producto varias veces
- La fecha de inicio del alquiler no puede ser pasada
- El año de publicación debe estar entre 1895 y 2026


## Instrucciones de Ejecución

### Requisitos

- **Java 21** o superior
- **Maven 3.6+**
- IDE recomendado: IntelliJ IDEA

### Pasos para Ejecutar

#### 1. Clonar el repositorio

```bash
git clone https://github.com/tu-usuario/AD_ProyectoSpring_AlquilerPeliculas.git
cd AD_ProyectoSpring_AlquilerPeliculas
```

#### 2. Ejecutar la aplicación

```bash
mvn spring-boot:run
```

#### 3. Inicializar datos de prueba

Al ejecutar la aplicación, se mostrará un menú interactivo en la consola:

```
╔══════════════════════════════════════════════════════════╗
║      SISTEMA DE ALQUILER DE PELÍCULAS Y SERIES            ║
╚══════════════════════════════════════════════════════════╝

  1. Inicializar datos de prueba
  2. Gestión de Categorías
  3. Gestión de Idiomas
  4. Gestión de Suscriptores
  5. Gestión de Productos
  6. Gestión de Alquileres
  7. Ver Estadísticas
  8. Ver Reportes
  9. Salir
```

**Selecciona opción 1** para cargar datos de prueba iniciales.

---

## Datos de prueba cargados

Al inicializar datos (opción 1 del menú), se crean:

### Categorías (5)
- Acción
- Drama
- Comedia
- Ciencia Ficción
- Terror

### Idiomas (4)
- Español
- Inglés
- Francés
- Italiano

### Suscriptores (4)
- 2 con plan BÁSICO (14 días)
- 2 con plan PREMIUM (28 días)

### Productos (6)
- **Películas:**
  - El Señor de los Anillos (2001)
  - Matrix (1999)
  - El Padrino (1972)
  - Toy Story (1995)
  
- **Series:**
  - Breaking Bad (2008)
  - Stranger Things (2016)

### Alquileres (4)
- 2 activos con plan BÁSICO
- 2 activos con plan PREMIUM

---

## VÍDEO CON DEMOSTRACIÓN DE PRUEBA EN POSTMAN

Breve prueba del funcionamiento de la API, se enfoca en la entidad de suscriptores

https://youtu.be/eKGmRkYIQOI

## Tecnologías Utilizadas
- Java 17
-  Maven
- Lombok
- Spring Boot
- Spring Data JPA
- Spring Security
- Hibernate
- H2 / Base de datos en memoria

---

Autor

**Victoria Nabou Diagne Briñas** para la asignatura de: **ACCESO A DATOS**

---
