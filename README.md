# SistemaGestionProyectos
Sistema para gestionar proyectos, guardar usuarios, entre otros. Conectado a una base de datos en PostgreSQL.
## ✅ Funcionalidades principales

- CRUD de Proyectos
- CRUD de Usuarios
- CRUD de Tareas
- Asignación de tareas a usuarios y proyectos
- Visualización del porcentaje de avance
- Interfaz gráfica con pestañas (`JTabbedPane`)
- Conexión a base de datos PostgreSQL mediante JDBC

---

## 🧩 Arquitectura del sistema

- **Lenguaje:** Java 17+
- **IDE:** NetBeans 24
- **Base de datos:** PostgreSQL 16
- **Gestor de versiones:** Git
- **Paradigma:** Programación orientada a objetos (POO)
- **Modelo de capas:**
  - `modelo/` → Clases POJO (`Proyecto`, `Usuario`, `Tarea`)
  - `dao/` → Clases DAO con JDBC (`ProyectoDB`, `UsuarioDB`, etc.)
  - `ui/` → Interfaces gráficas (`ProyectoPanel`, `MainSistema`)
  - `conexion/` → Clase `ConexionBD`

---

## ⚙️ Requisitos técnicos

- Java JDK 17 o superior
- NetBeans 24 o superior
- PostgreSQL 16+
- Driver JDBC PostgreSQL
- Git instalado (opcional)
