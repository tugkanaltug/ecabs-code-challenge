package ecabs.code.challenge.data.item

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "events")
data class Events(
    @PrimaryKey val id: String,
    val type: String,
    @field:Embedded(prefix = "actor_")
    val actor: Actor,
    @field:Embedded(prefix = "repo_")
    val repo: Repo,
    val created_at: String,
) : Parcelable