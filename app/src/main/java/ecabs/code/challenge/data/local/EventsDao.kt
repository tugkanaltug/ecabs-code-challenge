package ecabs.code.challenge.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ecabs.code.challenge.data.item.Events

/**
 * Defines methods for using the Events class with Room.
 */
@Dao
interface EventsDao {

    @Query("SELECT * FROM events order by events.created_at desc")
    fun get(): LiveData<List<Events>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(events: List<Events>)
}