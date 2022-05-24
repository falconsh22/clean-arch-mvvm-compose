package com.shahin.cleancompose.data.remote.searchArtists

import com.shahin.cleancompose.domain.repositories.searchArtists.SearchArtistsRepository
import javax.inject.Inject

class SearchArtistsArtistsRepositoryImpl @Inject constructor(
    private val searchArtistsApiService: SearchArtistsApiService
): SearchArtistsRepository {

}
