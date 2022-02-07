package com.example.peopletesting.network

import com.google.gson.Gson
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

/**
 * Mock API to call
 */
private const val BASE_URL =
    "https://5a51bfb150dffb001256e08f.mockapi.io/testing/"

/**
 * Okhttp builder
 */
val httpClient = OkHttpClient.Builder()

/**
 * Gson Object
 */

/**
 * Moshi
 */
//private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

/**
 * Retrofit builder
 */
private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .client(httpClient.build())
    .build()

interface PeopleApiService {
    @GET("people")
    suspend fun getPeople() : List<People>
}

object PeopleApi {
    val retrofitService : PeopleApiService by lazy {
        retrofit.create(PeopleApiService::class.java)
    }
}