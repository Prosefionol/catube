package com.example.catube.model

import com.squareup.moshi.Json

data class Video(
    @Json(name = "title") val videoTitle: String,
    @Json(name = "video_url") val videoUrl: String,
    @Json(name = "thumbnail_url") val videoCover: String,
    @Json(name = "duration") val videoDuration: Long
)

fun Video.toSimpleVideo(): SimpleVideo {
    return SimpleVideo(
        videoTitle = this.videoTitle,
        videoUrl = this.videoUrl
    )
}
