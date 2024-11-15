import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import android.util.Log

@Database(entities = [Crime::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun crimeDao(): CrimeDao

    companion object {
        fun getDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "crime_db"
            ).build()
        }
    }
}