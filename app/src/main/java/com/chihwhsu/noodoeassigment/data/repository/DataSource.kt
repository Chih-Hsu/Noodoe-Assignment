package com.chihwhsu.noodoeassigment.data.repository

import com.chihwhsu.noodoeassigment.data.Result
import com.chihwhsu.noodoeassigment.data.UserInformationBody
import com.chihwhsu.noodoeassigment.data.UserResult
import com.chihwhsu.noodoeassigment.network.NoodoeApi

interface DataSource {

    suspend fun logIn(userInfo: UserInformationBody): Result<UserResult>
}