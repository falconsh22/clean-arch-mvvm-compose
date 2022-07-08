package com.shahin.cleancompose.data.remote.albums.services

import com.shahin.cleancompose.data.remote.albums.models.response.Album
import com.shahin.cleancompose.network.models.GeneralPaginatedResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AlbumsApiService {

    @GET("artist/{id}/albums")
    suspend fun getArtistAlbumsByArtistID(
        @Query("id") artistId: String
    ): Response<GeneralPaginatedResponse<List<Album>>>
}
