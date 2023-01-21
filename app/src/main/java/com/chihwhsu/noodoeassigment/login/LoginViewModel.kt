package com.chihwhsu.noodoeassigment.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chihwhsu.noodoeassigment.UserManager
import com.chihwhsu.noodoeassigment.data.Result
import com.chihwhsu.noodoeassigment.data.UserInformationBody
import com.chihwhsu.noodoeassigment.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: Repository) : ViewModel() {

    private var _navigation = MutableLiveData<Boolean>().also { it.value = false }
    val navigation: LiveData<Boolean> get() = _navigation

    private var _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error


    private var userName: String? = null
    private var password: String? = null

    fun setUsername(userNameInput: String) {
        userName = userNameInput
    }

    fun setPassword(passwordInput: String) {
        password = passwordInput
    }


    fun logIn() {

        if (isFormatCorrect(userName, password)) {
            val userInfo = UserInformationBody(userName!!, password!!)

            viewModelScope.launch(Dispatchers.Default) {

                when (val result = repository.logIn(userInfo)) {

                    is Result.Success -> {
                        UserManager.user = result.data
                        _navigation.postValue(true)
                    }

                    is Result.Error -> {
                        _error.postValue(result.exception.message)
                    }

                    else -> {

                    }
                }
            }
        }
    }

    fun doneNavigation() {
        _navigation.value = false
    }

    private fun isFormatCorrect(userName: String?, password: String?): Boolean {
        return userName != null && password != null
    }

}