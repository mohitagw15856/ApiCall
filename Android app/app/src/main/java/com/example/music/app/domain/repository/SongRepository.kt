package com.example.music.app.domain.repository

import com.example.music.app.data.remote.dto.SongDto

interface SongRepository {
    suspend fun getSongs(): SongDto
}