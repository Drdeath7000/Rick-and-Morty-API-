package il.co.syntax.finalkotlinproject.data.loca_db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import il.co.syntax.finalkotlinproject.data.models.Character

@Database(entities = [Character::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun characterDao() : CharacterDao

    companion object {

        @Volatile
        private var instance : AppDatabase?  = null

        fun getDatabase(context: Context) : AppDatabase =
            instance ?: synchronized(this) {
                Room.databaseBuilder(context.applicationContext,AppDatabase::class.java,"characters")
                    .fallbackToDestructiveMigration().build().also {
                        instance = it
                    }
            }
    }
}

//you are using a library called Room, which handles all input/output operations