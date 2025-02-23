package com.example.catube.model

import com.squareup.moshi.Json

data class VideoResults(
    @Json(name = "results") val results: List<Video>
)
