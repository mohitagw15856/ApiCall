package com.example.music.app.data.remote

import com.example.music.app.data.remote.dto.SongDto
import retrofit2.http.GET

interface SongApi {
    @GET("limit=100/json")
    suspend fun getAllSongs(): SongDto
}