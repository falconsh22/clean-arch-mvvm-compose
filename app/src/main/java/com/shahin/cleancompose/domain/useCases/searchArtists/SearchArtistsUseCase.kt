package com.shahin.cleancompose.domain.useCases.searchArtists

import com.shahin.cleancompose.data.remote.searchArtists.models.response.Artist
import com.shahin.cleancompose.data.remote.searchArtists.models.response.SearchResponse
import com.shahin.cleancompose.domain.repositories.searchArtists.SearchArtistsRepository
import com.shahin.cleancompose.network.NetworkResponse
import javax.inject.Inject

class SearchArtistsUseCase @Inject constructor(
    private val searchArtistsRepository: SearchArtistsRepository
) {

    suspend fun searchArtistsByName(artistName: String): NetworkResponse<SearchResponse<List<Artist>>> {
        return searchArtistsRepository.searchArtistByName(
            artistName
        )
    }

}
