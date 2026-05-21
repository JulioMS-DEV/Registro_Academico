package ni.edu.uam.uamregister.repository

import kotlinx.coroutines.flow.Flow
import ni.edu.uam.uamregister.dao.EstudianteDao
import ni.edu.uam.uamregister.model.Estudiante

class EstudianteRepository(
    private val estudianteDao: EstudianteDao
) {

    val allEstudiantes: Flow<List<Estudiante>> =
        estudianteDao.getAllEstudiantes()

    suspend fun insertEstudiante(estudiante: Estudiante) {
        estudianteDao.insertEstudiante(estudiante)
    }

    suspend fun updateEstudiante(estudiante: Estudiante) {
        estudianteDao.updateEstudiante(estudiante)
    }

    suspend fun deleteEstudiante(estudiante: Estudiante) {
        estudianteDao.deleteEstudiante(estudiante)
    }
}