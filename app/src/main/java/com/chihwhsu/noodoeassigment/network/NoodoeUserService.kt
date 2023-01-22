package com.chihwhsu.noodoeassigment.network

import com.chihwhsu.noodoeassigment.data.User
import com.chihwhsu.noodoeassigment.data.UserInformationBody
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private const val BASE_URL = "https://noodoe-app-development.web.app/api/"

private val moshi = Moshi.Builder()
    .addLast(KotlinJsonAdapterFactory())
    .build()

private val client = OkHttpClient.Builder()
    .addInterceptor(
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    )
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .client(client)
    .build()

interface NoodoeService {

    @POST("login")
    suspend fun logIn(
        @Header("X-Parse-Application-Id") applicationId: String,
        @Body userInformation: UserInformationBody
    ): User
}

object NoodoeApi {
    val retrofitService: NoodoeService by lazy { retrofit.create(NoodoeService::class.java) }
}