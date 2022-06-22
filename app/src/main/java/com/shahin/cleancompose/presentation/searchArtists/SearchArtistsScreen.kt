package com.shahin.cleancompose.presentation.searchArtists

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shahin.cleancompose.ui.theme.Blue200
import com.shahin.cleancompose.ui.theme.Teal200
import com.shahin.cleancompose.ui.views.SearchArtistItemView
import kotlinx.coroutines.launch

@Composable
fun SearchArtistScreen(
    searchArtistsViewModel: SearchArtistsViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    var text by remember {
        mutableStateOf("")
    }
    val focusManager = LocalFocusManager.current
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
            val artistsState = searchArtistsViewModel.artists.observeAsState()
            LazyColumn {
                items(artistsState.value ?: emptyList()) { item ->
                    SearchArtistItemView(artist = item)
                }
            }
        }
    }
}


