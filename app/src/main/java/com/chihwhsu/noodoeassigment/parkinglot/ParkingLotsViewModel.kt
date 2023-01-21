package com.chihwhsu.noodoeassigment.parkinglot

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chihwhsu.noodoeassigment.data.DisplayParkingLots
import com.chihwhsu.noodoeassigment.data.repository.Repository

class ParkingLotsViewModel(private val repository: Repository) : ViewModel() {

    private var _parkingLots = MutableLiveData<List<DisplayParkingLots>>()
    val parkingLots : LiveData<List<DisplayParkingLots>> get() = _parkingLots


    init {
        val a = DisplayParkingLots("123","123","123",55,66,12,12)

        val list = listOf<DisplayParkingLots>(a)
        _parkingLots.value = list




    }
}