package com.chihwhsu.noodoeassigment.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chihwhsu.noodoeassigment.data.UserInformationBody
import com.chihwhsu.noodoeassigment.network.NoodoeApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {



    fun logIn(userInformationBody: UserInformationBody){
        viewModelScope.launch(Dispatchers.Default) {
            val applicationId = "vqYuKPOkLQLYHhk4QTGsGKFwATT4mBIGREI2m8eD"
            val result =  NoodoeApi.retrofitService.logIn(applicationId,userInformationBody)
            Log.d("testt","$result")
        }

    }
}