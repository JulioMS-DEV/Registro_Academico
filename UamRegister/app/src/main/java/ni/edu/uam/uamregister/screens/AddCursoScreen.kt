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
import ni.edu.uam.uamregister.model.Curso
import ni.edu.uam.uamregister.viewmodel.CursoViewModel

@Composable
fun AddCursoScreen(
    navController: NavController,
    cursoViewModel: CursoViewModel = viewModel()
) {

    var nombre by remember {
        mutableStateOf("")
    }

    var descripcion by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Agregar Curso",
            style = MaterialTheme.typography.headlineMedium
        )

        OutlinedTextField(
            value = nombre,
            onValueChange = {
                nombre = it
            },
            label = {
                Text("Nombre del Curso")
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = descripcion,
            onValueChange = {
                descripcion = it
            },
            label = {
                Text("Descripción")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {

                if (
                    nombre.isNotBlank() &&
                    descripcion.isNotBlank()
                ) {

                    val curso = Curso(
                        nombre = nombre,
                        descripcion = descripcion
                    )

                    cursoViewModel.insertCurso(curso)

                    navController.popBackStack()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {

            Text("Guardar Curso")
        }
    }
}