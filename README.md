```markdown
# UAM Register 🎓

**UAM Register** es una aplicación móvil desarrollada para Android utilizando **Jetpack Compose** y **Material Design 3**. El sistema está diseñado para la gestión eficiente de cursos académicos y estudiantes, permitiendo realizar operaciones CRUD básicas (Crear, Leer, Actualizar, Eliminar) bajo una arquitectura moderna y reactiva.

---

## 🚀 Características

* **Gestión de Cursos:** Registro, visualización y eliminación de asignaturas o cursos académicos.
* **Gestión de Estudiantes:** Registro completo de alumnos vinculándolos a un curso específico mediante su ID.
* **Interfaz Moderna:** Construida totalmente de forma declarativa con Jetpack Compose y siguiendo las pautas de Material Design 3.
* **Soporte de Tema Dinámico:** Adaptación automática a modo claro, modo oscuro y colores dinámicos (Android 12+).
* **Arquitectura MVVM:** Separación clara de responsabilidades para un código escalable y mantenible.

---

## 🛠️ Tecnologías y Herramientas Utilizadas

* **Lenguaje:** [Kotlin](https://kotlinlang.org/) (100%)
* **UI Framework:** [Jetpack Compose](https://developer.android.com/jetpack/compose) con Material Design 3.
* **Componentes de Arquitectura:**
    * **ViewModel:** Para la gestión de estados UI conscientes del ciclo de vida.
    * **Navigation Compose:** Para el flujo de pantallas y navegación de la app.
* **Persistencia de Datos:** [Room Database](https://developer.android.com/training/data-storage/room) (Implementado mediante patrones DAO y Repositorios con soporte de Corrutinas y `Flow`).
* **Asincronía:** Kotlin Coroutines & Asynchronous Data Streams (`Flow`, `StateFlow`).

---

## 📐 Arquitectura del Proyecto

El proyecto sigue el patrón de diseño arquitectónico **MVVM (Model-View-ViewModel)** recomendado por Google, estructurado en los siguientes paquetes:

```text
ni.edu.uam.uamregister/
│
├── dao/             # Interfaces de acceso a la base de datos (Room DAOs)
├── model/           # Clases de datos / Entidades (Curso, Estudiante)
├── repository/      # Capa de abstracción de datos (Repositorios)
├── viewmodel/       # Lógica de negocio y preparación de datos para la UI
├── navigation/      # Rutas y lógica de navegación de las pantallas
├── screens/         # Componentes visuales (UI/Screens en Compose)
└── ui/theme/        # Configuración de estilos, tipografías y paletas de colores

```

### Flujo de Datos Reactivo

La interfaz de usuario reacciona automáticamente a los cambios de la base de datos local utilizando flujos asíncronos:
`Room (Database) ➔ DAO ➔ Repository ➔ ViewModel (StateFlow) ➔ UI (Compose Screens)`

---

## 📱 Pantallas Principales

1. **Home Screen:** Panel central con accesos directos para la administración global del sistema.
2. **Curso List & Add Screen:** Listado de cursos integrados en un contenedor dinámico (`LazyColumn`) y formulario de registro con validaciones de campos no vacíos.
3. **Estudiante List & Add Screen:** Listado detallado de estudiantes matriculados y formulario para dar de alta nuevos alumnos asociándolos al ID de un curso.

---

## 🔧 Requisitos e Instalación

Para compilar y ejecutar este proyecto, asegúrate de contar con los siguientes entornos configurados:

1. **Android Studio** (Versión Ladybug o superior recomendada).
2. **JDK 17** o superior configurado en el IDE.
3. Un dispositivo físico Android o Emulador con **API 24 (Android 7.0)** como mínimo.

### Ejecución en el IDE:

1. Abre **Android Studio**.
2. Selecciona **Open** y busca la carpeta raíz de este proyecto.
3. Deja que Gradle sincronice todas las dependencias del sistema de manera automática.
4. Conecta tu dispositivo o inicia el emulador y presiona el botón **'Run'** (icono de play verde) en la barra superior.

---

## 📄 Licencia

Este proyecto se ha desarrollado con fines puramente académicos para la **Universidad Americana (UAM)**.

```

```
