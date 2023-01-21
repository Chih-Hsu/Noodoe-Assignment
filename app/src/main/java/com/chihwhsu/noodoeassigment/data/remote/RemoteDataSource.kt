package com.chihwhsu.noodoeassigment.data.remote

import com.chihwhsu.noodoeassigment.data.*
import com.chihwhsu.noodoeassigment.data.parking.AvailablePark
import com.chihwhsu.noodoeassigment.data.parking.Park
import com.chihwhsu.noodoeassigment.data.repository.DataSource
import com.chihwhsu.noodoeassigment.network.NoodoeApi
import com.chihwhsu.noodoeassigment.network.ParkingLotApi
import kotlinx.coroutines.*
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlinx.coroutines.Dispatchers

class RemoteDataSource : DataSource {

    override suspend fun logIn(userInfo: UserInformationBody): Result<User> {

        return try {
            val user = NoodoeApi.retrofitService.logIn(APPLICATION_ID, userInfo)
            Result.Success(user)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getAllParkingLots(): Result<List<DisplayParkingLots>> =

        suspendCoroutine { continuation ->
            val job = Job()
            val coroutineScope = CoroutineScope(Dispatchers.IO + job)
            val parkingLotsService = ParkingLotApi.retrofitService

            coroutineScope.launch {
                val parkingLotsResult = async { parkingLotsService.getAllParkingLots() }
                val availableResult = async { parkingLotsService.getAvailableParkingLots() }

                val parkingList = parkingLotsResult.await().data.park
                val availableList = availableResult.await().data.park

                try {
                    val displayList = createDisplayList(parkingList, availableList)
                    continuation.resume(Result.Success(displayList))
                } catch (e: Exception) {
                    continuation.resume(Result.Error(e))
                }

            }
        }

    private suspend fun createDisplayList(
        parkingList: List<Park>,
        availableList: List<AvailablePark>
    ): MutableList<DisplayParkingLots> {
        val displayList = mutableListOf<DisplayParkingLots>()

        for (item in parkingList) {

            val currentAvailableItemList = availableList.filter { it.id == item.id }

            if (currentAvailableItemList.isNotEmpty()) {
                withContext(Dispatchers.Default) {
                    val currentAvailableItem =
                        availableList.first { it.id == item.id }
                    val availableCar = currentAvailableItem.availableCar
                    val chargingStationList =
                        currentAvailableItem.chargeStation?.scoketStatusList
                    val standbyLotsList =
                        chargingStationList?.filter { it.spotStatus == "待機中" }
                    val chargingLotsList =
                        chargingStationList?.filter { it.spotStatus == "充電中" }

                    val newDisplayItem = DisplayParkingLots(
                        item.id,
                        item.name,
                        item.address,
                        item.totalCar,
                        availableCar,
                        chargingLotsList?.size ?: 0,
                        standbyLotsList?.size ?: 0
                    )
                    displayList.add(newDisplayItem)
                }
            }
        }
        return displayList
    }


    companion object {
        private const val APPLICATION_ID = "vqYuKPOkLQLYHhk4QTGsGKFwATT4mBIGREI2m8eD"
    }
}