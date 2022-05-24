package com.shahin.cleancompose.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.shahin.cleancompose.R
import com.shahin.cleancompose.data.remote.artist.models.Artist
import com.shahin.cleancompose.ui.theme.Typography

@Composable
fun SearchArtistItemView(artist: Artist) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                model = artist.pictureSmall,
                placeholder = painterResource(id = R.drawable.ic_round_photo_24)
            ),
            contentDescription = null,
            modifier = Modifier.size(60.dp)
        )
        Text(
            text = artist.name ?: "Unknown",
            modifier = Modifier
                .fillMaxWidth(),
            style = Typography.body1
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun SearchArtistItemViewPreview() {
    SearchArtistItemView(
        artist = Artist(
            name = "Adele"
        )
    )
}
