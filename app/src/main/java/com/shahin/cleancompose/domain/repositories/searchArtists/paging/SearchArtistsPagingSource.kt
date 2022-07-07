package com.shahin.cleancompose.domain.repositories.searchArtists.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.shahin.cleancompose.data.remote.searchArtists.models.response.Artist
import com.shahin.cleancompose.domain.repositories.searchArtists.SearchArtistsRepository
import com.shahin.cleancompose.network.NetworkResponse

class SearchArtistsPagingSource(
    private val artistName: String,
    private val searchArtistsRepository: SearchArtistsRepository
) : PagingSource<Int, Artist>() {

    private val pageSize = 25

    override fun getRefreshKey(state: PagingState<Int, Artist>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Artist> {
        if (artistName.isEmpty()) {
            return LoadResult.Error(Throwable("Nothing to show!"))
        }
        val nextPageNumber = params.key ?: 0
        when (
            val response = searchArtistsRepository.searchArtistByNamePaging(
                artistName = artistName,
                page = nextPageNumber
            )
        ) {
            is NetworkResponse.Success -> {
                val results = response.result ?: return LoadResult.Error(Throwable("No Data"))
                return LoadResult.Page(
                    data = results.data,
                    prevKey = null,
                    nextKey = if (results.data.isEmpty() || nextPageNumber * pageSize > results.total) null else nextPageNumber + 1
                )
            }
            is NetworkResponse.Failure -> {
                return LoadResult.Error(Throwable("Http failure"))
            }
            is NetworkResponse.NetworkError -> {
                return LoadResult.Error(Throwable("Network failure"))
            }
        }

    }
}
