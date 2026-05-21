package ni.edu.uam.uamregister.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ni.edu.uam.uamregister.model.Estudiante

@Dao
interface EstudianteDao {

    @Query("SELECT * FROM estudiantes")
    fun getAllEstudiantes(): Flow<List<Estudiante>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEstudiante(estudiante: Estudiante)

    @Update
    suspend fun updateEstudiante(estudiante: Estudiante)

    @Delete
    suspend fun deleteEstudiante(estudiante: Estudiante)
}