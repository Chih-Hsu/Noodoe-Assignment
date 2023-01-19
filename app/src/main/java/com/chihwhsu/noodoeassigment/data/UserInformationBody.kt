package com.chihwhsu.noodoeassigment.data

import com.squareup.moshi.Json

data class UserInformationBody(
    @Json(name = "username")val userName: String,
    val password: String
)
