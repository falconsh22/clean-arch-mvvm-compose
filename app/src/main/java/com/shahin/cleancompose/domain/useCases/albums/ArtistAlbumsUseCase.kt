package com.shahin.cleancompose.domain.useCases.albums

import com.shahin.cleancompose.data.remote.albums.models.response.Album
import com.shahin.cleancompose.domain.repositories.albums.ArtistAlbumsRepository
import com.shahin.cleancompose.network.NetworkResponse
import com.shahin.cleancompose.network.models.GeneralPaginatedResponse
import javax.inject.Inject

class ArtistAlbumsUseCase @Inject constructor(
    private val artistAlbumsRepository: ArtistAlbumsRepository
) {

    suspend fun getArtistAlbums(artistId: String): NetworkResponse<GeneralPaginatedResponse<List<Album>>> {
        return artistAlbumsRepository.getArtistAlbums(artistId = artistId)
    }

}
