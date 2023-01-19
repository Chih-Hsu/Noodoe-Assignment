package com.chihwhsu.noodoeassigment.data.remote

import com.chihwhsu.noodoeassigment.data.UserInformationBody
import com.chihwhsu.noodoeassigment.data.UserResult
import com.chihwhsu.noodoeassigment.data.repository.DataSource
import com.chihwhsu.noodoeassigment.network.NoodoeApi

class RemoteDataSource : DataSource {

    override suspend fun logIn(userInfo: UserInformationBody): UserResult {

        val applicationId = "vqYuKPOkLQLYHhk4QTGsGKFwATT4mBIGREI2m8eD"
        return NoodoeApi.retrofitService.logIn(applicationId, userInfo)
    }
}