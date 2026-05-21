package ni.edu.uam.uamregister.model
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "estudiantes",
    foreignKeys = [
        ForeignKey(
            entity = Curso::class,
            parentColumns = ["id"],
            childColumns = ["cursoId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Estudiante(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val nombres: String,

    val apellidos: String,

    val correo: String,

    val cursoId: Int
)