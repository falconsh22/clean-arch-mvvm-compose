package com.shahin.cleancompose.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.shahin.cleancompose.R
import com.shahin.cleancompose.data.remote.albums.models.response.Album

@Composable
fun ArtistAlbumItem(album: Album) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                model = album.coverMedium,
                placeholder = painterResource(id = R.drawable.ic_round_photo_24)
            ),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().height(100.dp)
        )
        Text(text = album.title ?: "")
    }

}

@Composable
@Preview(showSystemUi = true)
fun ArtistAlbumPreview() {
    ArtistAlbumItem(
        album = Album(
            id = 0,
            title = "Album title",
            releaseDate = "2020-12-12"
        )
    )
}
