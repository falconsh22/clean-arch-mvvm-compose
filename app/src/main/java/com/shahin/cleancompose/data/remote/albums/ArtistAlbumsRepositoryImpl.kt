package com.shahin.cleancompose.data.remote.albums

import com.shahin.cleancompose.data.remote.albums.models.response.Album
import com.shahin.cleancompose.data.remote.albums.services.AlbumsApiService
import com.shahin.cleancompose.domain.repositories.albums.ArtistAlbumsRepository
import com.shahin.cleancompose.network.NetworkResponse
import com.shahin.cleancompose.network.NetworkWrapper
import com.shahin.cleancompose.network.models.GeneralPaginatedResponse
import javax.inject.Inject

class ArtistAlbumsRepositoryImpl @Inject constructor(
    private val albumsApiService: AlbumsApiService
): NetworkWrapper(), ArtistAlbumsRepository {

    override suspend fun getArtistAlbums(artistId: String): NetworkResponse<GeneralPaginatedResponse<List<Album>>> {
        return networkResponseOf {
            albumsApiService.getArtistAlbumsByArtistID(
                artistId = artistId
            )
        }
    }
}
