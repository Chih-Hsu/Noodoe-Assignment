package com.chihwhsu.noodoeassigment.data.repository

import com.chihwhsu.noodoeassigment.data.DisplayParkingLots
import com.chihwhsu.noodoeassigment.data.Result
import com.chihwhsu.noodoeassigment.data.UserInformationBody
import com.chihwhsu.noodoeassigment.data.UserResult
import com.chihwhsu.noodoeassigment.data.parking.ParkingLotsResult


class DefaultRepository(
    private val remoteDataSource: DataSource
) : Repository {

    override suspend fun logIn(userInfo: UserInformationBody): Result<UserResult> {
        return remoteDataSource.logIn(userInfo)
    }

    override suspend fun getAllParkingLots(): Result<List<DisplayParkingLots>> {
        return remoteDataSource.getAllParkingLots()
    }

}