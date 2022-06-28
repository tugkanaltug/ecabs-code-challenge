package ecabs.code.challenge.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val title: LiveData<String> get() = titleData

    private var titleData: MutableLiveData<String> = MutableLiveData()

    fun setTitle(value: String) {
        titleData.value = value
    }
}