import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(tableName = "crime")
data class Crime(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "date") val date: Long,
    @ColumnInfo(name = "is_solved") val isSolved: Boolean,
    @ColumnInfo(name = "photo_path") val photoPath: String?
)