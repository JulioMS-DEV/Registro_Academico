package ni.edu.uam.uamregister.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Cursos : Screen("cursos")
    object AddCurso : Screen("add_curso?cursoId={cursoId}") {
        fun createRoute(cursoId: Int? = null) = if (cursoId != null) "add_curso?cursoId=$cursoId" else "add_curso"
    }
    object Estudiantes : Screen("estudiantes")
    object AddEstudiante : Screen("add_estudiante?estudianteId={estudianteId}") {
        fun createRoute(estudianteId: Int? = null) = if (estudianteId != null) "add_estudiante?estudianteId=$estudianteId" else "add_estudiante"
    }
}
