package com.example.catube.model.api

import com.example.catube.model.VideoResults
import retrofit2.http.GET

interface ApiService {
    @GET("person/23463954")
    suspend fun watchTV(): VideoResults
}
