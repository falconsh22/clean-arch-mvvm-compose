package com.shahin.cleancompose.data.remote.searchArtists.services

import com.shahin.cleancompose.commons.OrderType
import com.shahin.cleancompose.data.remote.searchArtists.models.response.Artist
import com.shahin.cleancompose.network.models.GeneralPaginatedResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchArtistsApiService {

    @GET("search/artist")
    suspend fun searchArtistByNameQuery(
        @Query("q") artistName: String,
        @Query("order") order: String = OrderType.Ranking.type,
        @Query("index") page: Int = 0,
        @Query("limit") limit: Int = 25
    ): Response<GeneralPaginatedResponse<List<Artist>>>

}
