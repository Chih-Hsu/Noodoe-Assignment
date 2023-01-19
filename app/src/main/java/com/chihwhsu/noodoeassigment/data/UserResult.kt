package com.chihwhsu.noodoeassigment.data

import com.squareup.moshi.Json

data class UserResult(
    @Json(name = "username")val userName: String,
    val phone: String,
    val createdAt: String,
    val updatedAt: String,
    val objectId: String,
    val sessionToken: String
)

//{
//    "username": "cooldude6",
//    "phone": "415-392-0202",
//    "createdAt": "2022-01-01T12:23:45.678Z",
//    "updatedAt": "2022-01-01T12:23:45.678Z",
//    "objectId": "g7y9tkhB7O",
//    "sessionToken": "r:pnktnjyb996sj4p156gjtp4im"
//}