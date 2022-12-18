package com.doodleblue.cryptocurrency.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.doodleblue.cryptocurrency.App
import com.doodleblue.cryptocurrency.data.db.CoinsDao
import com.doodleblue.cryptocurrency.data.model.Coins
import com.doodleblue.cryptocurrency.data.model.CoinsResponse
import com.doodleblue.cryptocurrency.db
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.concurrent.thread

class CoinsRepositoryImpl : CoinsRepository {

    private val coinsLiveData = MutableLiveData<List<Coins>>()
    private val coinsDao: CoinsDao = db.coinsDao()
    private val apiService = App.apiService
    private val allCoins: LiveData<List<Coins>>

    init {
        allCoins = coinsDao.getAll()
    }
    override fun getSavedCoins(): LiveData<List<Coins>> = allCoins

    override fun getCoinsLiveData() : MutableLiveData<List<Coins>>{
        return coinsLiveData
    }

    override fun saveCoins(coins: Coins) {
        thread {
            coinsDao.insert(coins)
        }
    }

    override fun fetchCoins() {

        apiService.getCoins().enqueue(object : Callback<CoinsResponse> {
            override fun onResponse(
                call: Call<CoinsResponse>,
                response: Response<CoinsResponse>
            ) {
                coinsLiveData.value = response.body()!!.data
            }

            override fun onFailure(call: Call<CoinsResponse>, t: Throwable) {
                Log.d(this.javaClass.simpleName, "Failure")
            }
        })
    }

}