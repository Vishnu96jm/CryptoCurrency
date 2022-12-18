package com.doodleblue.cryptocurrency.data.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.doodleblue.cryptocurrency.data.model.Coins

@Database(entities = [Coins::class], version = 1)
 abstract class CoinsDatabase  : RoomDatabase()  {

    abstract fun coinsDao(): CoinsDao

    companion object {
        private val lock = Any()
        private const val DB_NAME = "CoinsDatabase"
        private var INSTANCE: CoinsDatabase? = null

        fun getInstance(application: Application): CoinsDatabase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE =
                        Room.databaseBuilder(application, CoinsDatabase::class.java, DB_NAME)
                            .build()
                }
            }
            return INSTANCE!!
        }
    }
}