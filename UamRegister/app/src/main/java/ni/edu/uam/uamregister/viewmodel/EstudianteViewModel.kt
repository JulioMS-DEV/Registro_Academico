package ni.edu.uam.uamregister.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ni.edu.uam.uamregister.database.AppDatabase
import ni.edu.uam.uamregister.model.Estudiante
import ni.edu.uam.uamregister.repository.EstudianteRepository

class EstudianteViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository: EstudianteRepository

    val estudiantes: StateFlow<List<Estudiante>>

    init {

        val estudianteDao =
            AppDatabase
                .getDatabase(application)
                .estudianteDao()

        repository = EstudianteRepository(estudianteDao)

        estudiantes = repository.allEstudiantes.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )
    }

    fun insertEstudiante(estudiante: Estudiante) {

        viewModelScope.launch {
            repository.insertEstudiante(estudiante)
        }
    }

    fun updateEstudiante(estudiante: Estudiante) {

        viewModelScope.launch {
            repository.updateEstudiante(estudiante)
        }
    }

    fun deleteEstudiante(estudiante: Estudiante) {

        viewModelScope.launch {
            repository.deleteEstudiante(estudiante)
        }
    }
}