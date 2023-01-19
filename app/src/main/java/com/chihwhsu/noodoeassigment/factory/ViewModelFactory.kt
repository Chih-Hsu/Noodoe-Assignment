package com.chihwhsu.noodoeassigment.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chihwhsu.noodoeassigment.data.repository.DaggerRepositoryComponent
import com.chihwhsu.noodoeassigment.data.repository.Repository
import com.chihwhsu.noodoeassigment.login.LoginViewModel
import javax.inject.Inject

class ViewModelFactory() : ViewModelProvider.Factory {

    @Inject
    lateinit var repository: Repository

    override fun <T : ViewModel> create(modelClass: Class<T>): T =

        with(modelClass) {
            DaggerRepositoryComponent
                .create()
                .inject(this@ViewModelFactory)

            when {

                isAssignableFrom(LoginViewModel::class.java)
                -> LoginViewModel(repository)

                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}