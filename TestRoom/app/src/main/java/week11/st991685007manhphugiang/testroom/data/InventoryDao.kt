package week11.st991685007manhphugiang.testroom.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface InventoryDao {
    @Query("SELECT * FROM inventory")
    fun getAllItems(): Flow<List<InventoryItem>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: InventoryItem)
    @Delete
    suspend fun deleteItem(item: InventoryItem)
}