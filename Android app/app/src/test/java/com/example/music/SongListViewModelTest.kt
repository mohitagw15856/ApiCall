package com.example.music

import com.example.music.app.common.Resource
import com.example.music.app.domain.model.*
import com.example.music.app.domain.use_case.get_songs.GetSongsUseCase
import com.example.music.app.presentation.song_list.SongListState
import com.example.music.app.presentation.song_list.SongListViewModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(MockitoJUnitRunner::class)
class SongListViewModelTest{

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: SongListViewModel
    private var dummyList = ArrayList<Entry>()
    private lateinit var useCase : GetSongsUseCase

    @Before
    fun setup(){
        useCase = mockk<GetSongsUseCase>()
        var entry = Entry(
            Label("Happy Birthday"), emptyList(), Label("Happy Birthday"),
            Price("Happy Birthday", mockk()), mockk(), Label("Happy Birthday"), Label("Happy Birthday"),
            mockk(),
            mockk(),
            mockk(),
            mockk(),
            mockk()
        )

        dummyList.add(entry)

        entry = Entry(
            Label("Happy Birthday1"),
            emptyList(),
            Label("Happy Birthday1"),
            Price("Happy Birthday1", mockk()),
            mockk(),
            Label("Happy Birthday"),
            Label("Happy Birthday"),
            mockk(),
            mockk(),
            mockk(),
            mockk(),
            mockk()
        )

        dummyList.add(entry)

        entry = Entry(
            Label("Happy Birthday1"),
            emptyList(),
            Label("Happy Birthday1"),
            Price("Happy Birthday1", mockk()),
            mockk(),
            Label("Happy Birthday"),
            Label("Happy Birthday"),
            mockk(),
            mockk(),
            mockk(),
            mockk(),
            mockk()
        )

        dummyList.add(entry)

        entry = Entry(
            Label("Nice Song"),
            emptyList(),
            Label("Happy Birthday1"),
            Price("Happy Birthday1", mockk()),
            mockk(),
            Label("Happy Birthday"),
            Label("Happy Birthday"),
            mockk(),
            mockk(),
            mockk(),
            mockk(),
            mockk()
        )

        dummyList.add(entry)

        entry = Entry(
            Label("One Love"),
            emptyList(),
            Label("Happy Birthday1"),
            Price("Happy Birthday1", mockk()),
            mockk(),
            Label("Happy Birthday"),
            Label("Happy Birthday"),
            mockk(),
            mockk(),
            mockk(),
            mockk(),
            mockk()
        )

        dummyList.add(entry)
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun filterData() = runTest {

        every { useCase.invoke() } returns flow { emit(Resource.Success(dummyList)) }

        viewModel = SongListViewModel(useCase)

    val arrayList: ArrayList<Entry> = ArrayList<Entry>()

    viewModel.getFilteredList("Happy", dummyList, arrayList)
    assertEquals(3, arrayList.size)


    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun filterDataEmpty() = runTest {

        every { useCase.invoke() } returns flow { emit(Resource.Success(dummyList)) }

        viewModel = SongListViewModel(useCase)

        val arrayList: ArrayList<Entry> = ArrayList()

        viewModel.getFilteredList("test", dummyList, arrayList)
        assertEquals(0, arrayList.size)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getSongs() = runTest {

        every { useCase.invoke() } returns flow { emit(Resource.Error( "An unexpected error occurred")) }

        viewModel = SongListViewModel(useCase)

        viewModel.getSongs()

        advanceUntilIdle()
        assertEquals(viewModel.state.value, SongListState(
            error = "An unexpected error occurred"
        )
        )
    }

}