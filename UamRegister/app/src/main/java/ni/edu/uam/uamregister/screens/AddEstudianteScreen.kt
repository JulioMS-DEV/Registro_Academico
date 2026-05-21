package ni.edu.uam.uamregister.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import ni.edu.uam.uamregister.model.Curso
import ni.edu.uam.uamregister.model.Estudiante
import ni.edu.uam.uamregister.viewmodel.CursoViewModel
import ni.edu.uam.uamregister.viewmodel.EstudianteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEstudianteScreen(
    navController: NavController,
    estudianteViewModel: EstudianteViewModel = viewModel(),
    cursoViewModel: CursoViewModel = viewModel(),
    estudianteId: Int? = null
) {
    val estudiantes by estudianteViewModel.estudiantes.collectAsState()
    val cursos by cursoViewModel.cursos.collectAsState()
    
    val estudianteParaEditar = remember(estudianteId, estudiantes) {
        estudiantes.find { it.id == estudianteId }
    }

    var nombres by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var cursoId by remember { mutableStateOf("") }

    var expanded by remember { mutableStateOf(false) }
    val selectedCurso = cursos.find { it.id.toString() == cursoId }
    var cursoText by remember { mutableStateOf("") }

    LaunchedEffect(estudianteParaEditar) {
        estudianteParaEditar?.let {
            nombres = it.nombres
            apellidos = it.apellidos
            correo = it.correo
            cursoId = it.cursoId.toString()
        }
    }

    LaunchedEffect(selectedCurso) {
        cursoText = selectedCurso?.nombre ?: ""
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (estudianteId == null) "Agregar Estudiante" else "Editar Estudiante") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
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
                value = nombres,
                onValueChange = { nombres = it },
                label = { Text("Nombres") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = apellidos,
                onValueChange = { apellidos = it },
                label = { Text("Apellidos") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = correo,
                onValueChange = { correo = it },
                label = { Text("Correo") },
                modifier = Modifier.fillMaxWidth()
            )

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = cursoText,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Seleccionar Curso") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },
                    colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    cursos.forEach { curso ->
                        DropdownMenuItem(
                            text = {
                                Text(text = curso.nombre)
                            },
                            onClick = {
                                cursoId = curso.id.toString()
                                cursoText = curso.nombre
                                expanded = false
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                        )
                    }
                }
            }

            Button(
                onClick = {
                    if (nombres.isNotBlank() && apellidos.isNotBlank() && 
                        correo.isNotBlank() && cursoId.isNotBlank()) {
                        
                        val estudiante = Estudiante(
                            id = estudianteId ?: 0,
                            nombres = nombres,
                            apellidos = apellidos,
                            correo = correo,
                            cursoId = cursoId.toIntOrNull() ?: 0
                        )

                        if (estudianteId == null) {
                            estudianteViewModel.insertEstudiante(estudiante)
                        } else {
                            estudianteViewModel.updateEstudiante(estudiante)
                        }
                        navController.popBackStack()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (estudianteId == null) "Guardar Estudiante" else "Actualizar Estudiante")
            }
        }
    }
}
