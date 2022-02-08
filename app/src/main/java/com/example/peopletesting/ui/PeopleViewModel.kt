package com.example.peopletesting.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.peopletesting.network.People
//import com.example.peopletesting.network.PeopleApi
import com.example.peopletesting.network.PeopleApiService
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import java.lang.Exception

enum class PeopleApiStatus { LOADING, ERROR, DONE }

class PeopleViewModel(private var retrofitService: PeopleApiService) :ViewModel() {

    private val _status = MutableLiveData<PeopleApiStatus>()
    val status : LiveData<PeopleApiStatus> = _status

    private val _people = MutableLiveData<List<People>>()
    val people : LiveData<List<People>> = _people

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
                //TODO Change status to a error msg
                _status.value = PeopleApiStatus.ERROR
            }
        }
    }
}