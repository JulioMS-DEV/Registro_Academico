package ni.edu.uam.uamregister.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ni.edu.uam.uamregister.screens.AddCursoScreen
import ni.edu.uam.uamregister.screens.AddEstudianteScreen
import ni.edu.uam.uamregister.screens.CursoListScreen
import ni.edu.uam.uamregister.screens.EstudianteListScreen
import ni.edu.uam.uamregister.screens.HomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }

        composable(Screen.Cursos.route) {
            CursoListScreen(navController)
        }

        composable(
            route = Screen.AddCurso.route,
            arguments = listOf(
                navArgument("cursoId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) { backStackEntry ->
            val cursoId = backStackEntry.arguments?.getString("cursoId")?.toIntOrNull()
            AddCursoScreen(navController, cursoId = cursoId)
        }

        composable(Screen.Estudiantes.route) {
            EstudianteListScreen(navController)
        }

        composable(
            route = Screen.AddEstudiante.route,
            arguments = listOf(
                navArgument("estudianteId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) { backStackEntry ->
            val estudianteId = backStackEntry.arguments?.getString("estudianteId")?.toIntOrNull()
            AddEstudianteScreen(navController, estudianteId = estudianteId)
        }
    }
}
