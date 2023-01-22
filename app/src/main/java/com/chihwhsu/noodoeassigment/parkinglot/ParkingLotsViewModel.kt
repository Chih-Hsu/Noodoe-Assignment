package com.chihwhsu.noodoeassigment.parkinglot

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chihwhsu.noodoeassigment.data.DisplayParkingLots
import com.chihwhsu.noodoeassigment.data.Result
import com.chihwhsu.noodoeassigment.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ParkingLotsViewModel(private val repository: Repository) : ViewModel() {

    private var _parkingLots = MutableLiveData<List<DisplayParkingLots>?>()
    val parkingLots: LiveData<List<DisplayParkingLots>?> get() = _parkingLots

    private var _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading


    init {
        getData()
    }

    private fun getData() {

        _isLoading.value = true

        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getAllParkingLots()) {

                is Result.Success -> {
                    _parkingLots.postValue(result.data)
                    _isLoading.postValue(false)
                }

                is Result.Error -> {
                    _error.postValue(result.exception.message)
                    _isLoading.postValue(false)
                }

                else -> {
                    _isLoading.postValue(false)
                }
            }
        }
    }
}