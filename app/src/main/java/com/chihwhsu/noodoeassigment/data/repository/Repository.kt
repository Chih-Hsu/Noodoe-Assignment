package com.chihwhsu.noodoeassigment.data.repository

import com.chihwhsu.noodoeassigment.data.*

interface Repository {

    suspend fun logIn(userInfo: UserInformationBody): Result<User>

    suspend fun getAllParkingLots(): Result<List<DisplayParkingLots>>
}