package com.shahin.cleancompose.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.shahin.cleancompose.R
import com.shahin.cleancompose.data.remote.searchArtists.models.response.Artist
import com.shahin.cleancompose.ui.theme.Typography

@Composable
fun SearchArtistItemView(artist: Artist?) {
    Card(
        modifier = Modifier.padding(6.dp),
        elevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = artist?.pictureSmall ?: "",
                    placeholder = painterResource(id = R.drawable.ic_round_photo_24)
                ),
                contentDescription = null,
                modifier = Modifier.size(60.dp)
            )
            Text(
                text = artist?.name ?: stringResource(R.string.unknown),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 8.dp,
                        vertical = 0.dp
                    ),
                style = Typography.body1
            )
        }
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
