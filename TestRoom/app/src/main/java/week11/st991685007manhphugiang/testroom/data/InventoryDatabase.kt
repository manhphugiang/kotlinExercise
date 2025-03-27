package week11.st991685007manhphugiang.testroom.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [InventoryItem::class], version = 1,
    exportSchema = false
)

abstract class InventoryDataBase : RoomDatabase() {
    abstract fun inventoryDao(): InventoryDao

    companion object {
        @Volatile
        private var INSTANCE: InventoryDataBase? = null
        fun getDatabase(context: Context): InventoryDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    InventoryDataBase::class.java,
                    "My_inventory_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}