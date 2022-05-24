package com.shahin.cleancompose.presentation.searchArtists

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.shahin.cleancompose.ui.views.SearchArtistItemView
import kotlinx.coroutines.launch

@Composable
fun SearchArtistScreen(
    searchArtistsViewModel: SearchArtistsViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = "",
                onValueChange = {
                    coroutineScope.launch {
                        searchArtistsViewModel.searchArtistsByName(it)
                    }
                }
            )
            val artistsState = searchArtistsViewModel.artists.observeAsState()
            LazyColumn {
                items(artistsState.value ?: emptyList()) { item ->
                    SearchArtistItemView(artist = item)
                }
            }
        }
    }
}


