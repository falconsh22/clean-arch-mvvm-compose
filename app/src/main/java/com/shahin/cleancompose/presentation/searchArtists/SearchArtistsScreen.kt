package com.shahin.cleancompose.presentation.searchArtists

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.shahin.cleancompose.R
import com.shahin.cleancompose.ui.views.SearchArtistItemView

@Composable
fun SearchArtistScreen(
    searchArtistsViewModel: SearchArtistsViewModel = hiltViewModel(),
    navController: NavController
) {
    val focusManager = LocalFocusManager.current

    val lazyListState = rememberLazyListState()

    val artistNameFlow = searchArtistsViewModel.artistsName
    val artistNameText by artistNameFlow.collectAsState(initial = "")

    searchArtistsViewModel.runSearchQuery(
        query = artistNameFlow
    )

    val lazyPagingItems = searchArtistsViewModel.artistsFlow.collectAsLazyPagingItems()

    Column(modifier = Modifier.fillMaxSize()) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = artistNameText,
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            onValueChange = { textValue ->
                searchArtistsViewModel.queryArtistName(
                    updatedQuery = textValue
                )
            },
            label = {
                Text(text = stringResource(R.string.search_hint))
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            )
        )

        LazyColumn(
            modifier = Modifier.padding(6.dp),
            userScrollEnabled = true,
            state = lazyListState
        ) {

            items(lazyPagingItems) { item ->
                SearchArtistItemView(artist = item, navController = navController)
            }
            lazyPagingItems.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        if (this.itemCount == 0) {
                            item {
                                Text(
                                    text = stringResource(R.string.search_artist_name),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentWidth(Alignment.CenterHorizontally)
                                )
                            }
                        }
                    }
                    loadState.append is LoadState.Loading -> {
                        item {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentWidth(Alignment.CenterHorizontally)
                            )
                        }
                    }
                    loadState.append is LoadState.Error -> {
                        // Show error message
                    }
                }
            }
        }
    }
}


