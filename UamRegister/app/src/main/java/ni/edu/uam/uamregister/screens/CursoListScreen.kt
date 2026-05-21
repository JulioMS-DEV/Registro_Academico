package ni.edu.uam.uamregister.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import ni.edu.uam.uamregister.model.Curso
import ni.edu.uam.uamregister.navigation.Screen
import ni.edu.uam.uamregister.viewmodel.CursoViewModel

@Composable
fun CursoListScreen(
    navController: NavController,
    cursoViewModel: CursoViewModel = viewModel()
) {

    val cursos by cursoViewModel
        .cursos
        .collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Lista de Cursos",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigate(Screen.AddCurso.route)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Agregar Curso")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (cursos.isEmpty()) {

            Text(
                text = "No hay cursos registrados."
            )
        }

        else {

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(cursos) { curso ->

                    CursoItem(
                        curso = curso,
                        onDelete = {
                            cursoViewModel.deleteCurso(curso)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun CursoItem(
    curso: Curso,
    onDelete: () -> Unit
) {

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

            Text(
                text = curso.nombre,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = curso.descripcion
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {

                TextButton(
                    onClick = onDelete
                ) {

                    Text("Eliminar")
                }
            }
        }
    }
}