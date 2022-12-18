package com.doodleblue.cryptocurrency.data.networking

import com.doodleblue.cryptocurrency.App
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


fun buildClient(): OkHttpClient =
    OkHttpClient.Builder()
        .build()


fun buildRetrofit(): Retrofit {
    return Retrofit.Builder()
        .client(buildClient())
        .baseUrl(App.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create().asLenient())
        .build()
}

fun buildApiService(): RemoteAPIService =
    buildRetrofit().create(RemoteAPIService::class.java)