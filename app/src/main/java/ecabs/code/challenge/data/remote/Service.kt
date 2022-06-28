package ecabs.code.challenge.data.remote

import ecabs.code.challenge.data.item.Events
import retrofit2.Response
import retrofit2.http.GET

interface Service {

    @GET("events?per_page=5")
    suspend fun events(): Response<List<Events>>
}