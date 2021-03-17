package com.teenwolf3301.stormotiontesttask.ui.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.teenwolf3301.stormotiontesttask.data.MockarooData
import com.teenwolf3301.stormotiontesttask.data.MockarooRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: MockarooRepository,
    application: Application
) :
    AndroidViewModel(application) {

    private val _list = MutableLiveData<List<MockarooData>>()
    val list: LiveData<List<MockarooData>>
        get() = _list

    init {
        loadData()
    }

    private fun loadData() = viewModelScope.launch {
        _list.value = repository.getData()
    }
}