package com.shahin.cleancompose.presentation.albums

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shahin.cleancompose.commons.SingleLiveEvent
import com.shahin.cleancompose.data.remote.albums.models.response.Album
import com.shahin.cleancompose.domain.useCases.albums.ArtistAlbumsUseCase
import com.shahin.cleancompose.network.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArtistAlbumsViewModel @Inject constructor(
    private val artistAlbumsUseCase: ArtistAlbumsUseCase
) : ViewModel() {

    private val _albums = MutableLiveData<List<Album>>()
    val albums: LiveData<List<Album>> = _albums

    private val _error = SingleLiveEvent<String?>()
    val error: LiveData<String?> get() = _error

    suspend fun getAlbums(artistId: String) {
        when (val response = artistAlbumsUseCase.getArtistAlbums(artistId = artistId)) {
            is NetworkResponse.Success -> {
                _albums.postValue(
                    response.result?.data
                )
            }
            is NetworkResponse.Failure -> {
                _error.postValue(
                    response.errorReason.errorMessage
                )
            }
            is NetworkResponse.NetworkError -> {
                _error.postValue(
                    null
                )
            }
        }
    }

}
