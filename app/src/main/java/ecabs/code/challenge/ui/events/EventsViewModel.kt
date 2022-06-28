package ecabs.code.challenge.ui.events

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import dagger.hilt.android.lifecycle.HiltViewModel
import ecabs.code.challenge.data.repository.EventsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(
    repository: EventsRepository
) : ViewModel() {

    private val refresh = MutableLiveData(Unit)

    val request = refresh.switchMap {
        repository.request()
    }

    fun refresh() {
        refresh.value = Unit
    }
}