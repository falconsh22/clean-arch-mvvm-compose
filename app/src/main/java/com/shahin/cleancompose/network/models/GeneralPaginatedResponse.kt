package com.shahin.cleancompose.network.models

import com.google.gson.annotations.SerializedName

data class GeneralPaginatedResponse<T>(

    @field:SerializedName("next")
    val next: String,

    @field:SerializedName("total")
    val total: Int,

    @field:SerializedName("data")
    val data: T
)
