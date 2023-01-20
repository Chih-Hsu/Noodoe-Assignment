package com.chihwhsu.noodoeassigment.data.parking

import com.squareup.moshi.Json

data class AvailableInfo(
    @Json(name = "UPDATETIME") val updateTime: String,
    val park: List<AvailablePark>
)