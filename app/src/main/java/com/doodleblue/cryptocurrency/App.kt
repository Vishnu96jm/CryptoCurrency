package com.doodleblue.cryptocurrency

import android.app.Application
import com.doodleblue.cryptocurrency.data.db.CoinsDatabase
import com.doodleblue.cryptocurrency.data.networking.buildApiService


lateinit var db: CoinsDatabase

class App : Application() {

    companion object {
        private lateinit var instance: App
        const val BASE_URL = "https://api.coincap.io/v2/"

        //To expose the RemoteApi to the rest of the app
        val apiService by lazy { buildApiService() }

    }

    override fun onCreate() {
        super.onCreate()
        db = CoinsDatabase.getInstance(this)
        instance = this
    }

}
