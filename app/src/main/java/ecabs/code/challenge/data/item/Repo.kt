package ecabs.code.challenge.data.item

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Repo(
    val id: String,
    val name: String,
    val url: String,
) : Parcelable