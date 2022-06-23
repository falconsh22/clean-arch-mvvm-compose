package com.shahin.cleancompose.presentation.searchArtists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.shahin.cleancompose.data.remote.searchArtists.models.response.Artist
import com.shahin.cleancompose.domain.useCases.searchArtists.SearchArtistsUseCase
import com.shahin.cleancompose.network.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SearchArtistsViewModel @Inject constructor(
    private val searchArtistsUseCase: SearchArtistsUseCase
) : ViewModel() {

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

    fun searchArtistsByNamePaging(artistName: String): Flow<PagingData<Artist>> {
        return Pager(
            PagingConfig(
                pageSize = 25,
                enablePlaceholders = true,
                maxSize = 200
            )
        ) {
            searchArtistsUseCase.searchArtistsByNamePaging(artistName)
        }.flow.cachedIn(viewModelScope)
    }

}
