package ecabs.code.challenge.data.repository

import ecabs.code.challenge.data.local.EventsDao
import ecabs.code.challenge.data.remote.RemoteDataSource
import ecabs.code.challenge.common.performGetOperation
import javax.inject.Inject

class EventsRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: EventsDao
) {
    fun request() = performGetOperation(
        databaseQuery = { localDataSource.get() },
        networkCall = { remoteDataSource.events() },
        saveCallResult = { localDataSource.insert(it) }
    )
}