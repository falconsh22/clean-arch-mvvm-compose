package com.shahin.cleancompose.data.remote.searchArtists

import com.shahin.cleancompose.data.remote.searchArtists.models.response.Artist
import com.shahin.cleancompose.data.remote.searchArtists.models.response.SearchResponse
import com.shahin.cleancompose.data.remote.searchArtists.services.SearchArtistsApiService
import com.shahin.cleancompose.domain.repositories.searchArtists.SearchArtistsRepository
import com.shahin.cleancompose.network.NetworkResponse
import com.shahin.cleancompose.network.NetworkWrapper
import javax.inject.Inject

class SearchArtistsArtistsRepositoryImpl @Inject constructor(
    private val searchArtistsApiService: SearchArtistsApiService
) : NetworkWrapper(), SearchArtistsRepository {

    override suspend fun searchArtistByName(
        artistName: String
    ): NetworkResponse<SearchResponse<List<Artist>>> {
        return networkResponseOf {
            searchArtistsApiService.searchArtistByNameQuery(
                artistName = artistName
            )
        }
    }

    override suspend fun searchArtistByNamePaging(
        artistName: String,
        page: Int
    ): NetworkResponse<SearchResponse<List<Artist>>> {
        return networkResponseOf {
            searchArtistsApiService.searchArtistByNameQuery(
                artistName = artistName,
                page = page
            )
        }
    }

}
