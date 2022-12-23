package com.doodleblue.cryptocurrency.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.doodleblue.cryptocurrency.data.CoinsRepository
import com.doodleblue.cryptocurrency.data.CoinsRepositoryImpl
import com.doodleblue.cryptocurrency.data.model.Coins
import com.doodleblue.cryptocurrency.data.networking.InternetChecker

@RequiresApi(Build.VERSION_CODES.M)
class MainViewModel(private val repository: CoinsRepository = CoinsRepositoryImpl())
    : ViewModel() {

    private val allCoins = MediatorLiveData<List<Coins>>()

    init {
        getAllCoins()
    }

    fun getSavedCoins() = allCoins

    private fun getAllCoins() {
        allCoins.addSource(repository.getSavedCoins()) { list ->
            allCoins.postValue(list)
        }
    }

    fun fetchCoins() {
        return repository.fetchCoins()
    }

    fun saveMovie(coins: Coins) {
        repository.saveCoins(coins)
    }

    fun getCoinsLiveData() : MutableLiveData<List<Coins>?> {
        return repository.getCoinsLiveData()
    }

    fun getInternetStatus() : LiveData<Boolean>{
       return repository.getInternetStatus()
    }

}
