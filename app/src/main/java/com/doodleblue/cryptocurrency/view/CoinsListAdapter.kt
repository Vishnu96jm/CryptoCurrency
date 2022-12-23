
package com.doodleblue.cryptocurrency.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.doodleblue.cryptocurrency.R
import com.doodleblue.cryptocurrency.data.model.Coins
import com.doodleblue.cryptocurrency.databinding.ItemListBinding


class CoinsListAdapter(private val coinsList: MutableList<Coins>) :
    RecyclerView.Adapter<CoinsListAdapter.CoinsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinsViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoinsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinsViewHolder, position: Int) {
        holder.bindData(coinsList[position])
    }

    override fun getItemCount(): Int = coinsList.size

    fun setCoins(coinsList: List<Coins>) {
        this.coinsList.clear()
        this.coinsList.addAll(coinsList)
        notifyDataSetChanged()
    }

    inner class CoinsViewHolder(binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root){

             private val symbol: TextView = binding.itemSymbol
             private val name : TextView = binding.itemName
             private val price : TextView = binding.itemPrice
             private val change : TextView = binding.itemChange
             private val view = binding.root
             fun bindData(coins : Coins){
                 symbol.text = coins.symbol.toString()
                 name.text = coins.name.toString()
                 price.text = view.resources.getString(R.string.dollar, String.format("%.02f", coins.priceUsd!!.toFloat()))
                 change.text = view.resources.getString(R.string.percent, String.format("%.03f", coins.changePercent24Hr!!.toFloat()))+"%"
             }
         }


    }

