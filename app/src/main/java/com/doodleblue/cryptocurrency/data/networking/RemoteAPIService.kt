package com.doodleblue.cryptocurrency.data.networking

import com.doodleblue.cryptocurrency.data.model.CoinsResponse
import retrofit2.Call
import retrofit2.http.GET

interface RemoteAPIService {

    @GET("assets")
    fun getCoins(): Call<CoinsResponse>

}