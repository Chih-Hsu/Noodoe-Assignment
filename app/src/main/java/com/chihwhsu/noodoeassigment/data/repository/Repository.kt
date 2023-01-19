package com.chihwhsu.noodoeassigment.data.repository

import com.chihwhsu.noodoeassigment.data.Result
import com.chihwhsu.noodoeassigment.data.UserInformationBody
import com.chihwhsu.noodoeassigment.data.UserResult

interface Repository {

    suspend fun logIn(userInfo: UserInformationBody): Result<UserResult>
}