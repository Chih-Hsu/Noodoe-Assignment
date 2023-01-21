package com.chihwhsu.noodoeassigment.timezone

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chihwhsu.noodoeassigment.UserManager
import com.chihwhsu.noodoeassigment.data.NoodoeTimeZone
import com.chihwhsu.noodoeassigment.data.User
import com.chihwhsu.noodoeassigment.data.repository.Repository
import java.util.*

class TimeZoneViewModel(private val repository: Repository) : ViewModel() {

    private var _user = MutableLiveData<User?>()
    val user: LiveData<User?> get() = _user

    private var _timeZoneList = MutableLiveData<List<NoodoeTimeZone>>()
    val timeZoneList: LiveData<List<NoodoeTimeZone>> get() = _timeZoneList

    init {
        getData()
    }

    private fun getData() {
        getUser()
        getTimeZone()
    }

    private fun getTimeZone() {
        val timeZoneList = mutableListOf<NoodoeTimeZone>()

        for (id in TimeZone.getAvailableIDs()) {
            val displayName = TimeZone.getTimeZone(id).displayName
            val newTimeZone = NoodoeTimeZone(id, displayName)
            timeZoneList.add(newTimeZone)
        }
        _timeZoneList.value = timeZoneList
    }

    private fun getUser() {
        val user = UserManager.user
        _user.value = user
    }
}