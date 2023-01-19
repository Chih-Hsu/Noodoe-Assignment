package com.chihwhsu.noodoeassigment.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chihwhsu.noodoeassigment.data.Result
import com.chihwhsu.noodoeassigment.data.UserInformationBody
import com.chihwhsu.noodoeassigment.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: Repository) : ViewModel() {


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
                when(val result = repository.logIn(userInfo)){

                    is Result.Success -> {
                        Log.d("testt", "$result")
                    }

                    is Result.Error -> {
                        Log.d("testt", "${result.exception}")
                    }

                    else ->{

                    }

                }

            }
        }
    }

    private fun isFormatCorrect(userName: String?, password: String?): Boolean {
        return userName != null && password != null
    }
}