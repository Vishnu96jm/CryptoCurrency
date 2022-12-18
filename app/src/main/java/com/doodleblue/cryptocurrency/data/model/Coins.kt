package com.doodleblue.cryptocurrency.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json


@Entity(tableName = "coins")
data class Coins(

    @PrimaryKey
    @field:Json(name = "id") var id: String = "",

    @field:Json(name = "rank") var rank: String? = null,

    @field:Json(name = "symbol") var symbol: String? = null,

    @field:Json(name = "name") var name: String? = null,

    @field:Json(name = "supply") var supply: String? = null,

    @field:Json(name = "maxSupply") var maxSupply: String? = null,

    @field:Json(name = "marketCapUsd") var marketCapUsd: String? = null,

    @field:Json(name = "volumeUsd24Hr") var volumeUsd24Hr: String? = null,

    @field:Json(name = "priceUsd") var priceUsd: String? = null,

    @field:Json(name = "changePercent24Hr") var changePercent24Hr: String? = null,

    @field:Json(name = "vwap24Hr") var vwap24Hr: String? = null,

    @field:Json(name = "explorer") var explorer: String? = null

    )

