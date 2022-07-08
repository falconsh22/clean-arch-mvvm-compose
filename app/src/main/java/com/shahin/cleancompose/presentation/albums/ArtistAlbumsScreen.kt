package com.shahin.cleancompose.presentation.albums

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shahin.cleancompose.ui.views.ArtistAlbumItem
import kotlinx.coroutines.launch

@Composable
fun ArtistAlbumsScreen(
    artistId: String?,
    artistAlbumsViewModel: ArtistAlbumsViewModel = hiltViewModel()
) {
    val id = artistId ?: return

    val coroutineScope = rememberCoroutineScope()

    val albums = artistAlbumsViewModel.albums.observeAsState(initial = emptyList())

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        userScrollEnabled = true,
        modifier = Modifier.padding(8.dp)
    ) {
        coroutineScope.launch {
            artistAlbumsViewModel.getAlbums(artistId = id)
        }
        items(albums.value) { item ->
            ArtistAlbumItem(album = item)
        }
    }

}
