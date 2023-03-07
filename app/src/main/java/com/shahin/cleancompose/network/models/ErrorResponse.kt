package com.shahin.cleancompose.network.models

import com.google.gson.annotations.SerializedName

data class ErrorResponse(

	@field:SerializedName("error")
	val error: Error
)

