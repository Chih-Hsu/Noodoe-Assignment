package com.chihwhsu.noodoeassigment.data.remote

import com.chihwhsu.noodoeassigment.data.Result
import com.chihwhsu.noodoeassigment.data.UserInformationBody
import com.chihwhsu.noodoeassigment.data.UserResult
import com.chihwhsu.noodoeassigment.data.repository.DataSource
import com.chihwhsu.noodoeassigment.network.NoodoeApi

class RemoteDataSource : DataSource {

    override suspend fun logIn(userInfo: UserInformationBody): Result<UserResult> {

        val applicationId = "vqYuKPOkLQLYHhk4QTGsGKFwATT4mBIGREI2m8eD"

        return try {
            val userResult = NoodoeApi.retrofitService.logIn(applicationId, userInfo)
            Result.Success(userResult)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}