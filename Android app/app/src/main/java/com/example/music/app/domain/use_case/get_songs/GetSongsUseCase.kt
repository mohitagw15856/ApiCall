package com.example.music.app.domain.use_case.get_songs

import com.example.music.app.common.Resource
import com.example.music.app.data.remote.dto.toSong
import com.example.music.app.domain.model.Entry
import com.example.music.app.domain.repository.SongRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetSongsUseCase @Inject constructor(
    private val repository: SongRepository
) {
    operator fun invoke(): Flow<Resource<List<Entry>>> = flow {
        try {
            emit(Resource.Loading())
            val songs = repository.getSongs().toSong()?.feed?.entry

            if(songs != null){
                emit(Resource.Success(songs))
            } else {
                emit(Resource.Error("Unable to load data. Check your internet connection and try again."))
            }

        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Unable to reach server. Check your internet connection and try again."))
        }
    }
}