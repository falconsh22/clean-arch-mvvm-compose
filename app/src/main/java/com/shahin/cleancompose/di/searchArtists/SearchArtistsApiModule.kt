package com.shahin.cleancompose.di.searchArtists

import com.shahin.cleancompose.data.remote.searchArtists.SearchArtistsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit


@Module
@InstallIn(SingletonComponent::class)
object SearchArtistsApiModule {

    @Provides
    fun provideArtistsApi(
        retrofit: Retrofit
    ): SearchArtistsApiService {
        return retrofit.create(SearchArtistsApiService::class.java)
    }

}
