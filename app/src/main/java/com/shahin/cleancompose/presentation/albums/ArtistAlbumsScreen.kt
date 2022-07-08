package com.shahin.cleancompose.presentation.albums

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import com.shahin.cleancompose.data.remote.searchArtists.models.response.Artist
import kotlinx.coroutines.launch

@Composable
fun ArtistAlbumsScreen(
    artist: Artist,
    artistAlbumsViewModel: ArtistAlbumsViewModel = hiltViewModel()
) {
    val artistId = artist.id ?: return

    val coroutineScope = rememberCoroutineScope()
    
    val albums = artistAlbumsViewModel.albums.observeAsState(initial = emptyList())

    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        coroutineScope.launch {
            artistAlbumsViewModel.getAlbums(artistId = artistId)
        }
        items(albums.value) {

        }
    }

}
