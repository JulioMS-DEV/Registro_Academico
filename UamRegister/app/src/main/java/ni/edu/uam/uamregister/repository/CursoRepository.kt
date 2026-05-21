package ni.edu.uam.uamregister.repository

import kotlinx.coroutines.flow.Flow
import ni.edu.uam.uamregister.dao.CursoDao
import ni.edu.uam.uamregister.model.Curso

class CursoRepository(
    private val cursoDao: CursoDao
) {

    val allCursos: Flow<List<Curso>> =
        cursoDao.getAllCursos()

    suspend fun insertCurso(curso: Curso) {
        cursoDao.insertCurso(curso)
    }

    suspend fun updateCurso(curso: Curso) {
        cursoDao.updateCurso(curso)
    }

    suspend fun deleteCurso(curso: Curso) {
        cursoDao.deleteCurso(curso)
    }
}