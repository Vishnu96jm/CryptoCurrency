package com.doodleblue.cryptocurrency.data.model

import com.squareup.moshi.Json

data class CoinsResponse(
    @field:Json(name = "data") var data: List<Coins>? = null
)
