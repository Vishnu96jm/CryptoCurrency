package com.doodleblue.cryptocurrency.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.doodleblue.cryptocurrency.data.model.Coins

@Dao
interface CoinsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(coins: Coins)

    @Query("select * from coins")
    fun getAll(): LiveData<List<Coins>>

}