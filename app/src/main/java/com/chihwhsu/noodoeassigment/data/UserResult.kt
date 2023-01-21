package com.chihwhsu.noodoeassigment.data

import com.squareup.moshi.Json


data class User(
    @Json(name = "name")val name: String ="",
    val phone: String ="",
    val timezone: String,
    val createdAt: String ="",
    val updatedAt: String ="",
    val objectId: String ="",
    val sessionToken: String =""
)
