package com.example.music.app.presentation.song_list

import androidx.annotation.OpenForTesting
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.music.app.common.Resource
import com.example.music.app.domain.model.Entry
import com.example.music.app.domain.use_case.get_songs.GetSongsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.Collections.emptyList
import javax.inject.Inject

@OpenForTesting
@HiltViewModel
class SongListViewModel @Inject constructor(
    private val getSongsUseCase: GetSongsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(SongListState())
    val state: State<SongListState> = _state
    private var originalSongsList = ArrayList<Entry>()

    init {
        getSongs()
    }

    fun search(keyword: String) {
        _state.value = SongListState(isLoading = true)
        val resultFilteredList = ArrayList<Entry>()
        getFilteredList(keyword, originalSongsList, resultFilteredList)
        if (resultFilteredList.isNotEmpty()) {
            _state.value = SongListState(songs = resultFilteredList)
        } else {
            if (keyword.isEmpty()) {
                _state.value = SongListState(songs = originalSongsList)
            } else {
                _state.value = SongListState(
                    error = "Search data not found"
                )
            }
        }
    }

    internal fun getFilteredList(
        keyword: String,
        originalSongsList: ArrayList<Entry>,
        resultFilteredList: ArrayList<Entry>
    )  {
        if (keyword.isNotEmpty()) {
            for (song in originalSongsList) {
                if (song.imName.label.contains(keyword, true)) {
                    resultFilteredList.add(song)
                }
            }
        }
    }

    internal fun getSongs() {
        getSongsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = SongListState(songs = result.data ?: emptyList())
                    originalSongsList = result.data as ArrayList<Entry>
                }
                is Resource.Error -> {
                    _state.value = SongListState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = SongListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}