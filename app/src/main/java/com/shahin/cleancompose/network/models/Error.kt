package com.shahin.cleancompose.network.models

import com.google.gson.annotations.SerializedName

data class Error(

    @field:SerializedName("code")
    val code: Int,

    @field:SerializedName("type")
    val type: String,

    @field:SerializedName("message")
    val message: String
)
