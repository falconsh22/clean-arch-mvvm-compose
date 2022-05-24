package com.shahin.cleancompose.presentation.searchArtists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shahin.cleancompose.data.remote.artist.models.Artist
import com.shahin.cleancompose.domain.useCases.searchArtists.SearchArtistsUseCase
import com.shahin.cleancompose.network.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchArtistsViewModel @Inject constructor(
    private val searchArtistsUseCase: SearchArtistsUseCase
): ViewModel() {

    private val _artists: MutableLiveData<List<Artist>> = MutableLiveData()
    val artists: LiveData<List<Artist>> = _artists

    suspend fun searchArtistsByName(artistName: String) {
        when (val results = searchArtistsUseCase.searchArtistsByName(artistName)) {
            is NetworkResponse.Success -> {
                _artists.postValue(results.result?.data)
            }
            is NetworkResponse.Failure -> TODO()
            is NetworkResponse.NetworkError -> TODO()
        }
    }

}
