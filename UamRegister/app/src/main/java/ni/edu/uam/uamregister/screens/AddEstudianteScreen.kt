package ni.edu.uam.uamregister.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import ni.edu.uam.uamregister.model.Estudiante
import ni.edu.uam.uamregister.viewmodel.EstudianteViewModel

@Composable
fun AddEstudianteScreen(
    navController: NavController,
    estudianteViewModel: EstudianteViewModel = viewModel()
) {

    var nombres by remember {
        mutableStateOf("")
    }

    var apellidos by remember {
        mutableStateOf("")
    }

    var correo by remember {
        mutableStateOf("")
    }

    var cursoId by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Agregar Estudiante",
            style = MaterialTheme.typography.headlineMedium
        )

        OutlinedTextField(
            value = nombres,
            onValueChange = {
                nombres = it
            },
            label = {
                Text("Nombres")
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = apellidos,
            onValueChange = {
                apellidos = it
            },
            label = {
                Text("Apellidos")
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = correo,
            onValueChange = {
                correo = it
            },
            label = {
                Text("Correo")
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = cursoId,
            onValueChange = {
                cursoId = it
            },
            label = {
                Text("ID del Curso")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {

                if (
                    nombres.isNotBlank() &&
                    apellidos.isNotBlank() &&
                    correo.isNotBlank() &&
                    cursoId.isNotBlank()
                ) {

                    val estudiante = Estudiante(
                        nombres = nombres,
                        apellidos = apellidos,
                        correo = correo,
                        cursoId = cursoId.toInt()
                    )

                    estudianteViewModel.insertEstudiante(estudiante)

                    navController.popBackStack()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {

            Text("Guardar Estudiante")
        }

        Button(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {

            Text("Volver")
        }
    }
}