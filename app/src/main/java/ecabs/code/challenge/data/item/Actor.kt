package ecabs.code.challenge.data.item

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Actor(
    val id: String,
    val login: String,
    val display_login: String,
    val gravatar_id: String,
    val url: String,
    val avatar_url: String
) : Parcelable