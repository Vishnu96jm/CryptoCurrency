package com.doodleblue.cryptocurrency.view

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.doodleblue.cryptocurrency.data.model.Coins
import com.doodleblue.cryptocurrency.databinding.ActivityMainBinding
import com.doodleblue.cryptocurrency.viewmodel.MainViewModel
import java.util.*

@RequiresApi(Build.VERSION_CODES.M)
class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private val adapter = CoinsListAdapter(mutableListOf())
    private val localPreferences by lazy { getPreferences(Context.MODE_PRIVATE) }

    companion object{
        const val KEY_LOADED_PREFS = "internet_status"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.pullToRefresh.setOnRefreshListener {
            viewModel.getInternetStatus()
            localPreferences.edit().putBoolean(KEY_LOADED_PREFS, false)
                .apply()
        }

        viewModel.getInternetStatus().observe(this){ status ->
            if (status && !localPreferences.getBoolean(KEY_LOADED_PREFS, false)){
                localPreferences.edit().putBoolean(KEY_LOADED_PREFS, true)
                    .apply()
                viewModel.fetchCoins()

            }else if (localPreferences.getBoolean(KEY_LOADED_PREFS, false)){
                binding.pullToRefresh.isRefreshing = false
            }else{
                binding.pullToRefresh.isRefreshing = false
                Toast.makeText(this, "Connect to Internet!!", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.getCoinsLiveData().observe(this) { coins ->
            if (coins == null) {
                binding.pullToRefresh.isRefreshing = false
                Toast.makeText(this, "Fetch failed, pull to refresh!!", Toast.LENGTH_SHORT).show()
            } else {
                for (coin in coins) {
                    viewModel.saveMovie(coin)
                }
                   binding.pullToRefresh.isRefreshing = false
            }
        }

        viewModel.getSavedCoins().observe(this, Observer { coinsList ->
            coinsList?.let {
                adapter.setCoins(coinsList)
            }
        })

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    search(newText)
                }
                return true
            }
        })
    }



    private fun search(text: String) {
        val filteredNewsList = mutableListOf<Coins>()

        viewModel.getSavedCoins().observe(this, Observer { coinsList ->
            coinsList?.let {
                for (coin in coinsList){
                    if (coin.name!!.lowercase().contains(text.lowercase(Locale.getDefault()))) {
                        filteredNewsList.add(coin)
                    }
                }
            }
        })

        if (filteredNewsList.isEmpty()) {
            adapter.setCoins(mutableListOf())
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show()
        } else {
            adapter.setCoins(filteredNewsList)
        }
    }
}