package com.chihwhsu.noodoeassigment.data.parking

import com.squareup.moshi.Json

data class ParkingLotsInformation(
    @Json(name = "UPDATETIME") val updateTime: String,
    val park: List<Park>
)