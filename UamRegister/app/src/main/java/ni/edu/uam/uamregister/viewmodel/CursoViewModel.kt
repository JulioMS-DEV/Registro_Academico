package ni.edu.uam.uamregister.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ni.edu.uam.uamregister.database.AppDatabase
import ni.edu.uam.uamregister.model.Curso
import ni.edu.uam.uamregister.repository.CursoRepository

class CursoViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository: CursoRepository

    val cursos: StateFlow<List<Curso>>

    init {

        val cursoDao =
            AppDatabase
                .getDatabase(application)
                .cursoDao()

        repository = CursoRepository(cursoDao)

        cursos = repository.allCursos.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )
    }

    fun insertCurso(curso: Curso) {

        viewModelScope.launch {
            repository.insertCurso(curso)
        }
    }

    fun updateCurso(curso: Curso) {

        viewModelScope.launch {
            repository.updateCurso(curso)
        }
    }

    fun deleteCurso(curso: Curso) {

        viewModelScope.launch {
            repository.deleteCurso(curso)
        }
    }
}