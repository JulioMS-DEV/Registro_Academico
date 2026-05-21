package ni.edu.uam.uamregister.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import ni.edu.uam.uamregister.model.Curso
import ni.edu.uam.uamregister.viewmodel.CursoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCursoScreen(
    navController: NavController,
    cursoViewModel: CursoViewModel = viewModel(),
    cursoId: Int? = null
) {
    val cursos by cursoViewModel.cursos.collectAsState()
    val cursoParaEditar = remember(cursoId, cursos) {
        cursos.find { it.id == cursoId }
    }

    var nombre by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }

    // Cargar datos si estamos editando
    LaunchedEffect(cursoParaEditar) {
        cursoParaEditar?.let {
            nombre = it.nombre
            descripcion = it.descripcion
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (cursoId == null) "Agregar Curso" else "Editar Curso") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre del Curso") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = descripcion,
                onValueChange = { descripcion = it },
                label = { Text("Descripción") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    if (nombre.isNotBlank() && descripcion.isNotBlank()) {
                        val curso = Curso(
                            id = cursoId ?: 0,
                            nombre = nombre,
                            descripcion = descripcion
                        )
                        if (cursoId == null) {
                            cursoViewModel.insertCurso(curso)
                        } else {
                            cursoViewModel.updateCurso(curso)
                        }
                        navController.popBackStack()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (cursoId == null) "Guardar Curso" else "Actualizar Curso")
            }
        }
    }
}
