package com.shahin.cleancompose.di.albums

import com.shahin.cleancompose.data.remote.albums.services.AlbumsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ArtistAlbumsApiModule {

    @Provides
    @Singleton
    fun provideArtistAlbumsApi(
        retrofit: Retrofit
    ): AlbumsApiService {
        return retrofit.create(AlbumsApiService::class.java)
    }

}
