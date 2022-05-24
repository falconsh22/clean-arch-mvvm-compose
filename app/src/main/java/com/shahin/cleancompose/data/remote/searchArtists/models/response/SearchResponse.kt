package com.shahin.cleancompose.data.remote.searchArtists.models.response

import com.google.gson.annotations.SerializedName

data class SearchResponse<T>(

	@field:SerializedName("next")
	val next: String,

	@field:SerializedName("total")
	val total: Int,

	@field:SerializedName("data")
	val data: List<T>
)
