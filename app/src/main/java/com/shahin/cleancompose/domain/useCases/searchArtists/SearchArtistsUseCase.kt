package com.shahin.cleancompose.domain.useCases.searchArtists

import com.shahin.cleancompose.data.remote.searchArtists.models.response.Artist
import com.shahin.cleancompose.domain.repositories.searchArtists.SearchArtistsRepository
import com.shahin.cleancompose.domain.repositories.searchArtists.paging.SearchArtistsPagingSource
import com.shahin.cleancompose.network.NetworkResponse
import com.shahin.cleancompose.network.models.GeneralPaginatedResponse
import javax.inject.Inject

class SearchArtistsUseCase @Inject constructor(
    private val searchArtistsRepository: SearchArtistsRepository
) {

    suspend fun searchArtistsByName(artistName: String): NetworkResponse<GeneralPaginatedResponse<List<Artist>>> {
        return searchArtistsRepository.searchArtistByName(
            artistName
        )
    }

    fun searchArtistsByNamePaging(artistName: String): SearchArtistsPagingSource {
        return SearchArtistsPagingSource(
            artistName = artistName,
            searchArtistsRepository = searchArtistsRepository
        )
    }

}
