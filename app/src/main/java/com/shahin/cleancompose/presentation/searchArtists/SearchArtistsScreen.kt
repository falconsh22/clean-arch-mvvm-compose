package com.shahin.cleancompose.presentation.searchArtists

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import androidx.paging.compose.itemsIndexed
import com.shahin.cleancompose.domain.repositories.searchArtists.paging.SearchArtistsPagingSource
import com.shahin.cleancompose.ui.theme.Blue200
import com.shahin.cleancompose.ui.theme.Teal200
import com.shahin.cleancompose.ui.views.SearchArtistItemView
import kotlinx.coroutines.launch

@Composable
fun SearchArtistScreen(
    searchArtistsViewModel: SearchArtistsViewModel = hiltViewModel()
) {
    val focusManager = LocalFocusManager.current

    val coroutineScope = rememberCoroutineScope()
    var text by remember {
        mutableStateOf("")
    }

    val pager = remember {
        searchArtistsViewModel.searchArtistsByNamePaging(
            artistName = text
        )
    }

    val lazyPagingItems = pager.collectAsLazyPagingItems()

    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = text,
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Blue200,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                onValueChange = {
                    text = it
                    coroutineScope.launch {
                        searchArtistsViewModel.searchArtistsByName(it)
                    }
                },
                label = {
                    Text(text = "Search something ...")
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

            LazyColumn {

                if (lazyPagingItems.loadState.refresh == LoadState.Loading) {
                    item {
                        Text(
                            text = "Loading ...",
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentWidth(Alignment.CenterHorizontally)
                        )
                    }
                }

                items(lazyPagingItems) { item ->
                    SearchArtistItemView(artist = item)
                }

                if (lazyPagingItems.loadState.append == LoadState.Loading) {
                    item {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentWidth(Alignment.CenterHorizontally)
                        )
                    }
                }
            }
        }
    }
}


