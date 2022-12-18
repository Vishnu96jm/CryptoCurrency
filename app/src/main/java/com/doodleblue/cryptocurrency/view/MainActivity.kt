package com.doodleblue.cryptocurrency.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.doodleblue.cryptocurrency.data.model.Coins
import com.doodleblue.cryptocurrency.databinding.ActivityMainBinding
import com.doodleblue.cryptocurrency.viewmodel.MainViewModel
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private val adapter = CoinsListAdapter(mutableListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.pullToRefresh.setOnRefreshListener {
            viewModel.fetchCoins()
        }

        viewModel.fetchCoins()
        viewModel.getCoinsLiveData().observe(this) { coins ->
            if (coins == null) {
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
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show()
        } else {
            adapter.setCoins(filteredNewsList)
        }
    }
}