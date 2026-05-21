package ni.edu.uam.uamregister.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ni.edu.uam.uamregister.model.Curso

@Dao
interface CursoDao {

    @Query("SELECT * FROM cursos")
    fun getAllCursos(): Flow<List<Curso>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurso(curso: Curso)

    @Update
    suspend fun updateCurso(curso: Curso)

    @Delete
    suspend fun deleteCurso(curso: Curso)
}