package com.example.catube.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SimpleVideo(
    val videoUrl: String,
    val videoTitle: String
): Parcelable