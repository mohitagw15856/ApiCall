package com.example.music.app.presentation.song_list

import android.widget.Toast
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.music.R
import com.example.music.app.presentation.song_list.components.SongListItems
import com.example.music.app.util.TestTags


@Composable
fun SongListScreen(
    viewModel: SongListViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state = viewModel.state.value
    Box(
        modifier = Modifier
            .fillMaxSize()
            .testTag(TestTags.SONGS_SECTION)
    ) {
        Column {
            ExpandableSearchView(
                searchDisplay = "",
                onSearchDisplayChanged = { searchText ->
                    viewModel.search(searchText)
                },
                expandedInitially = false
            )
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.songs) { song ->
                    SongListItems(
                        song = song,
                        onItemClick = {
                            Toast.makeText(
                                context,
                                "Selected ${song.imName.label}",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    )
                }
            }
        }
        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(10.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End,
        ) {
            FloatingActionButton(
                onClick = {
                    viewModel.getSongs()
                },
                backgroundColor = Color.Blue,
                contentColor = Color.White
            ) {
                Icon(Icons.Filled.Refresh, "")
            }
        }
    }
}

@Composable
fun SearchIcon(iconTint: Color) {
    Icon(
        painter = painterResource(id = R.drawable.ic_search),
        contentDescription = "search icon",
        tint = iconTint
    )
}


@Composable
fun CollapsedSearchView(
    onExpandedChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colors.onPrimary,
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.app_name),
            color = Color.Black,
            style = MaterialTheme.typography.h6,
            modifier = Modifier
                .padding(start = 16.dp)
        )
        IconButton(onClick = { onExpandedChanged(true) }) {
            SearchIcon(iconTint = tint)
        }
    }
}

@Composable
fun ExpandedSearchView(
    searchDisplay: String,
    onSearchDisplayChanged: (String) -> Unit,
    onExpandedChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colors.onPrimary,
) {
    val focusManager = LocalFocusManager.current

    val textFieldFocusRequester = remember { FocusRequester() }

    // Can be used to request focus on the textfield by default
    /*     SideEffect {
             textFieldFocusRequester.requestFocus()
         }*/

    var textFieldValue by remember {
        mutableStateOf(TextFieldValue(searchDisplay, TextRange(searchDisplay.length)))
    }

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {
            onExpandedChanged(false)
            onSearchDisplayChanged("")
        }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "back icon",
                tint = tint
            )
        }
        TextField(
            textStyle = TextStyle(color = Color.Black),
            value = textFieldValue,
            onValueChange = {
                textFieldValue = it
                if (it.text.isEmpty()) {
                    onSearchDisplayChanged(it.text)
                }
            },
            trailingIcon = {
                IconButton(onClick = {
                    onSearchDisplayChanged(textFieldValue.text)
                    focusManager.clearFocus()

                }) {
                    SearchIcon(iconTint = tint)

                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(textFieldFocusRequester),
            label = {
                Text(text = "Search", color = Color.Black)
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                    onSearchDisplayChanged(textFieldValue.text)

                }
            )
        )
    }
}

@Composable
fun ExpandableSearchView(
    searchDisplay: String,
    onSearchDisplayChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    expandedInitially: Boolean = false,
    tint: Color = colorResource(id = R.color.purple_500),
) {
    val (expanded, onExpandedChanged) = remember {
        mutableStateOf(expandedInitially)
    }


    Crossfade(targetState = expanded) { isSearchFieldVisible ->
        when (isSearchFieldVisible) {
            true -> ExpandedSearchView(
                searchDisplay = searchDisplay,
                onSearchDisplayChanged = onSearchDisplayChanged,
                onExpandedChanged = onExpandedChanged,
                modifier = modifier,
                tint = tint
            )

            false -> CollapsedSearchView(
                onExpandedChanged = onExpandedChanged,
                modifier = modifier,
                tint = tint
            )
        }
    }
}
