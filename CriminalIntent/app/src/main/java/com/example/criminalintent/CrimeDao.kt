import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CrimeDao {
    @Insert
    fun insert(crime: Crime)

    @Query("SELECT * FROM crime")
    fun getAllCrimes(): LiveData<List<Crime>>
}