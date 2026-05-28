# Sistema de Gestión de Centros Educativos

Este repositorio contiene el **Proyecto I** del Segundo Cuatrimestre, enfocado en el desarrollo de un sistema modular para la administración integral de instituciones escolares. La aplicación está construida sobre **Java Standard Edition (Java SE)** y utiliza persistencia de datos nativa mediante archivos planos `.txt`.

## Tecnologías y Entorno
* **Lenguaje:** Java (JDK 8 o superior)
* **Estructura del Proyecto:** Proyecto estándar de NetBeans (`Ant`)
* **Persistencia:** Archivos de texto plano orientados a registros

---

## Arquitectura del Sistema

### 1. Componentes de la Lógica de Negocio (`src/Logica/`)
* **`GestionCentrosEducativos.java`**: Modulo principal y orquestador que implementa el flujo del `SistemaGestionEscolar`. Administra las operaciones del menú interactivo y la toma de decisiones.
* **`Control.java`**: Controlador encargado del procesamiento, parseo y manipulación directa de las estructuras de datos antes de interactuar con la persistencia.

### 2. Estructura de Almacenamiento (Persistencia Local)
La aplicación almacena, lee y actualiza la información académica de manera dinámica mediante los siguientes archivos:
* **`estudiantes.txt`**: Padrón y datos generales de los alumnos matriculados.
* **`docentes.txt`**: Registro del personal académico y sus credenciales de especialización.
* **`cursos.txt`**: Catálogo completo de las materias que se imparten en el centro educativo.
* **`aulas.txt`**: Inventario de la infraestructura física del plantel.
* **`asignacionesCompletas.txt`**: Matriz de vinculación que cruza qué profesor imparte qué curso y en qué aula determinada.
* **`notas.txt`**: Historial de calificaciones individuales asociadas a las asignaciones.

---

##  Instrucciones de Ejecución

### Requisitos Previos
* Contar con el Java Development Kit (JDK) instalado.
* Se recomienda el uso de **NetBeans IDE** o **Apache NetBeans** para una importación directa de la suite de propiedades.

### Pasos para Correr el Proyecto
1. Clona el repositorio en tu espacio de trabajo local.
2. Abre la carpeta `GestionCentrosEducativos` directamente desde tu IDE de preferencia.
3. Asegúrate de que los archivos `.txt` ubicados en la raíz del proyecto cuenten con los permisos de lectura y escritura correctos en tu sistema operativo.
4. Limpia, construye y ejecuta la clase principal desde la terminal del IDE o presionando `F6`.