package com.example.music.app.data.repository

import com.example.music.app.data.remote.SongApi
import com.example.music.app.data.remote.dto.SongDto
import com.example.music.app.domain.repository.SongRepository
import javax.inject.Inject

class SongRepositoryImpl @Inject constructor(
    private val api: SongApi
) : SongRepository {
    override suspend fun getSongs(): SongDto {
        return api.getAllSongs()
    }

}