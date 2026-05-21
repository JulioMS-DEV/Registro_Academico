package ni.edu.uam.uamregister.navigation

sealed class Screen(val route: String) {

    object Home : Screen("home")

    object Cursos : Screen("cursos")

    object AddCurso : Screen("add_curso")

    object Estudiantes : Screen("estudiantes")

    object AddEstudiante : Screen("add_estudiante")
}