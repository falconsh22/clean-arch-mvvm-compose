package com.shahin.cleancompose.domain.repositories.searchArtists

import com.shahin.cleancompose.data.remote.searchArtists.models.response.Artist
import com.shahin.cleancompose.network.NetworkResponse
import com.shahin.cleancompose.network.models.GeneralPaginatedResponse

interface SearchArtistsRepository {

    suspend fun searchArtistByName(
        artistName: String
    ): NetworkResponse<GeneralPaginatedResponse<List<Artist>>>

    suspend fun searchArtistByNamePaging(
        artistName: String,
        page: Int
    ): NetworkResponse<GeneralPaginatedResponse<List<Artist>>>
}
