package com.shahin.cleancompose.di.searchArtists

import com.shahin.cleancompose.data.remote.searchArtists.SearchArtistsRepositoryImpl
import com.shahin.cleancompose.domain.repositories.searchArtists.SearchArtistsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class SearchArtistsModule {

    @Singleton
    @Binds
    abstract fun bindSearchRepository(
        searchArtistsRepositoryImpl: SearchArtistsRepositoryImpl
    ): SearchArtistsRepository

}
