package ecabs.code.challenge.data.remote

import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val service: Service
) : BaseDataSource() {

    suspend fun events() = getResult { service.events() }
}