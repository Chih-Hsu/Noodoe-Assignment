package com.chihwhsu.noodoeassigment.data.parking

import com.squareup.moshi.Json

data class ScoketStatus(
    @Json(name = "spot_abrv") val spotAbrv: String,
    @Json(name = "spot_status") val spotStatus: String
)