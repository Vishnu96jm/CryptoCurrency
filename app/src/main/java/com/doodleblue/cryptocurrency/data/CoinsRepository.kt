package com.doodleblue.cryptocurrency.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.doodleblue.cryptocurrency.data.model.Coins

interface CoinsRepository {

    fun getSavedCoins(): LiveData<List<Coins>>

    fun saveCoins(coins: Coins)

    fun fetchCoins()

    fun getCoinsLiveData() : MutableLiveData<List<Coins>?>

    fun getInternetStatus() : MutableLiveData<Boolean>
}
