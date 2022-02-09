package com.example.peopletesting.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.peopletesting.network.People
import com.example.peopletesting.network.PeopleApiService
import kotlinx.coroutines.launch

enum class PeopleApiStatus { LOADING, ERROR, DONE }

class PeopleViewModel(private var retrofitService: PeopleApiService) :ViewModel() {

    private val _status = MutableLiveData<PeopleApiStatus>()

    private val _people = MutableLiveData<List<People>>()

    init {
        getPeopleDetails()
    }

    fun getPeopleDetails(){
        viewModelScope.launch {
            _status.value = PeopleApiStatus.LOADING
            try {
                _people.value = retrofitService.getPeople()
                _status.value = PeopleApiStatus.DONE
            }
            catch (e: Exception){
                _status.value = PeopleApiStatus.ERROR
            }
        }
    }

    fun getPeople(): LiveData<List<People>>{
        return _people
    }

    fun getStatus() : LiveData<PeopleApiStatus> {
        return _status
    }
}