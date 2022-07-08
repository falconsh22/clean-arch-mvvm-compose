package com.shahin.cleancompose.presentation

sealed class Screen(val route: String) {
    object SearchArtists : Screen("search_artists")
    object ArtistAlbums : Screen("artist_albums")
}
