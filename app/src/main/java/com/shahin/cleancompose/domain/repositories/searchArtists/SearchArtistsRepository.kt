package com.shahin.cleancompose.domain.repositories.searchArtists

import com.shahin.cleancompose.data.remote.searchArtists.models.response.Artist
import com.shahin.cleancompose.data.remote.searchArtists.models.response.SearchResponse
import com.shahin.cleancompose.network.NetworkResponse

interface SearchArtistsRepository {

    suspend fun searchArtistByName(
        artistName: String
    ): NetworkResponse<SearchResponse<List<Artist>>>

    suspend fun searchArtistByNamePaging(
        artistName: String,
        page: Int
    ): NetworkResponse<SearchResponse<List<Artist>>>
}
