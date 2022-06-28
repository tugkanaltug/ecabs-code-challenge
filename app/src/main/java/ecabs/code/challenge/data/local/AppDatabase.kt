package ecabs.code.challenge.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ecabs.code.challenge.data.item.Events

/**
 * A database that stores Events information.
 * And a global method to get access to the database.
 */
@Database(entities = [Events::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun eventsDao(): EventsDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "AppDatabase")
                .fallbackToDestructiveMigration()
                .build()
    }
}