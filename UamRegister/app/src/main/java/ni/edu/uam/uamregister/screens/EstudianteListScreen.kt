package ni.edu.uam.uamregister.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import ni.edu.uam.uamregister.model.Estudiante
import ni.edu.uam.uamregister.navigation.Screen
import ni.edu.uam.uamregister.viewmodel.EstudianteViewModel

@Composable
fun EstudianteListScreen(
    navController: NavController,
    estudianteViewModel: EstudianteViewModel = viewModel()
) {

    val estudiantes by estudianteViewModel
        .estudiantes
        .collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Lista de Estudiantes",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigate(Screen.AddEstudiante.route)
            },
            modifier = Modifier.fillMaxWidth()
        ) {

            Text("Agregar Estudiante")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (estudiantes.isEmpty()) {

            Text(
                text = "No hay estudiantes registrados."
            )
        }

        else {

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(estudiantes) { estudiante ->

                    EstudianteItem(
                        estudiante = estudiante,

                        onDelete = {
                            estudianteViewModel.deleteEstudiante(estudiante)
                        },

                        onUpdate = { estudianteActualizado ->
                            estudianteViewModel.updateEstudiante(estudianteActualizado)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun EstudianteItem(
    estudiante: Estudiante,
    onDelete: () -> Unit,
    onUpdate: (Estudiante) -> Unit
) {

    var isEditing by remember {
        mutableStateOf(false)
    }

    var nombres by remember {
        mutableStateOf(estudiante.nombres)
    }

    var apellidos by remember {
        mutableStateOf(estudiante.apellidos)
    }

    var correo by remember {
        mutableStateOf(estudiante.correo)
    }

    var cursoId by remember {
        mutableStateOf(estudiante.cursoId.toString())
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            if (isEditing) {

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

                Spacer(modifier = Modifier.height(8.dp))

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

                Spacer(modifier = Modifier.height(8.dp))

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

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = cursoId,
                    onValueChange = {
                        cursoId = it
                    },
                    label = {
                        Text("Curso ID")
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {

                    TextButton(
                        onClick = {

                            val estudianteActualizado = estudiante.copy(
                                nombres = nombres,
                                apellidos = apellidos,
                                correo = correo,
                                cursoId = cursoId.toInt()
                            )

                            onUpdate(estudianteActualizado)

                            isEditing = false
                        }
                    ) {

                        Text("Guardar")
                    }
                }
            }

            else {

                Text(
                    text = "${estudiante.nombres} ${estudiante.apellidos}",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = estudiante.correo
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Curso ID: ${estudiante.cursoId}"
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {

                    TextButton(
                        onClick = {
                            isEditing = true
                        }
                    ) {

                        Text("Editar")
                    }

                    TextButton(
                        onClick = onDelete
                    ) {

                        Text("Eliminar")
                    }
                }
            }
        }
    }
}