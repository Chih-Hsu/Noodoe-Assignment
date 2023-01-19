package com.chihwhsu.noodoeassigment.data.repository

import com.chihwhsu.noodoeassigment.data.UserInformationBody
import com.chihwhsu.noodoeassigment.data.UserResult


class DefaultRepository(
    val remoteDataSource: DataSource
) : Repository {

    override suspend fun logIn(userInfo: UserInformationBody): UserResult {
        return remoteDataSource.logIn(userInfo)
    }

}