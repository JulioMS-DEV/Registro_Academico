package ni.edu.uam.uamregister.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ni.edu.uam.uamregister.dao.CursoDao
import ni.edu.uam.uamregister.dao.EstudianteDao
import ni.edu.uam.uamregister.model.Curso
import ni.edu.uam.uamregister.model.Estudiante

@Database(
    entities = [
        Curso::class,
        Estudiante::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cursoDao(): CursoDao

    abstract fun estudianteDao(): EstudianteDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {

            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "uam_register_db"
                ).build()

                INSTANCE = instance

                instance
            }
        }
    }
}