package com.chihwhsu.noodoeassigment.data.repository

import com.chihwhsu.noodoeassigment.data.*
import com.chihwhsu.noodoeassigment.data.parking.ParkingLotsResult


class DefaultRepository(
    private val remoteDataSource: DataSource
) : Repository {

    override suspend fun logIn(userInfo: UserInformationBody): Result<User> {
        return remoteDataSource.logIn(userInfo)
    }

    override suspend fun getAllParkingLots(): Result<List<DisplayParkingLots>> {
        return remoteDataSource.getAllParkingLots()
    }

}