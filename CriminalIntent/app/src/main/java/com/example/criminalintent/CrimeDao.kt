import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CrimeDao {
    @Insert
    suspend fun insert(crime: Crime)

    @Query("SELECT * FROM crime")
    suspend fun getAllCrimes(): Flow<List<Crime>>

    @Query("DELETE FROM crime WHERE id = :crimeId")
    suspend fun deleteCrimeById(crimeId: Long)
}