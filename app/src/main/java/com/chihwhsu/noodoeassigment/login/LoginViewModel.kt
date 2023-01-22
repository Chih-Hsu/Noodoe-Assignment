package com.chihwhsu.noodoeassigment.login

import android.util.Patterns
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

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private var userName: String? = null
    private var password: String? = null

    fun setUsername(userNameInput: String) {
        userName = userNameInput
    }

    fun setPassword(passwordInput: String) {
        password = passwordInput
    }

    fun isEmailFormatCorrect(): Boolean {
        return !userName.isNullOrEmpty() &&
                Patterns.EMAIL_ADDRESS.matcher(userName).matches()
    }

    fun isPasswordFormatCorrect(): Boolean {
        return !password.isNullOrEmpty()
    }

    fun logIn() {

        _isLoading.value = true

        // Already check userName and password are not null
        val userInfo = UserInformationBody(userName!!, password!!)

        viewModelScope.launch(Dispatchers.Default) {

            when (val result = repository.logIn(userInfo)) {

                is Result.Success -> {
                    UserManager.user = result.data
                    _isLoading.postValue(false)
                    _navigation.postValue(true)
                }

                is Result.Error -> {
                    _isLoading.postValue(false)
                    _error.postValue(result.exception.message)
                }

                else -> {
                    _isLoading.postValue(false)
                }
            }
        }
    }

    fun doneNavigation() {
        _navigation.value = false
    }

}