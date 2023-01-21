package com.chihwhsu.noodoeassigment.data


data class DisplayParkingLots(
    val id: String,
    val name: String,
    val address: String,
    val totalCar: Int,
    val availableCar: Int,
    val charging: Int?,
    val standby: Int?
)
