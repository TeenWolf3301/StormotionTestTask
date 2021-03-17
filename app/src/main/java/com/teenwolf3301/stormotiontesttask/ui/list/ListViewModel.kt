package com.teenwolf3301.stormotiontesttask.ui.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.teenwolf3301.stormotiontesttask.data.MockarooData
import com.teenwolf3301.stormotiontesttask.data.MockarooRepository
import com.teenwolf3301.stormotiontesttask.utility.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: MockarooRepository,
    application: Application
) :
    AndroidViewModel(application) {

    private val _list = MutableLiveData<Resource<List<MockarooData>>>()
    val list: LiveData<Resource<List<MockarooData>>>
        get() = _list

    init {
        getData()
    }

    private fun getData() = viewModelScope.launch {
        _list.postValue(Resource.loading(null))
        try {
            repository.getData().let {
                if (it.isNotEmpty()) _list.postValue(Resource.success(it))
            }
        } catch (exception: Exception) {
            _list.postValue(Resource.error(exception.toString(), null))
        }
    }
}