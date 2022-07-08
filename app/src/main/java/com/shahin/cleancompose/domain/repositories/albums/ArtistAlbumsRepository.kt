package com.shahin.cleancompose.domain.repositories.albums

import com.shahin.cleancompose.data.remote.albums.models.response.Album
import com.shahin.cleancompose.network.NetworkResponse
import com.shahin.cleancompose.network.models.GeneralPaginatedResponse

interface ArtistAlbumsRepository {

    suspend fun getArtistAlbums(artistId: String): NetworkResponse<GeneralPaginatedResponse<List<Album>>>

}
