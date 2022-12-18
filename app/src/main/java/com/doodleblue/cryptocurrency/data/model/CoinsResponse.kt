package com.doodleblue.cryptocurrency.data.model

import com.squareup.moshi.Json
import java.math.BigInteger

data class CoinsResponse(
    @field:Json(name = "data") var data: List<Coins>? = null
)
