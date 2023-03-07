package com.shahin.cleancompose.di.albums

import com.shahin.cleancompose.data.remote.albums.ArtistAlbumsRepositoryImpl
import com.shahin.cleancompose.domain.repositories.albums.ArtistAlbumsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ArtistAlbumsModule {

    @Binds
    @Singleton
    abstract fun bindArtistAlbumsRepository(
        artistAlbumsRepositoryImpl: ArtistAlbumsRepositoryImpl
    ): ArtistAlbumsRepository

}
