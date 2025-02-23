package com.example.catube.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class VideoPlayerViewModel(
    private val savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _playbackPosition = savedStateHandle.getLiveData<Long>(KEY_POSITION)
    val playbackPosition: LiveData<Long> = _playbackPosition

    init {
        savedStateHandle[KEY_POSITION] = 0L
    }

    fun setPlaybackPosition(position: Long) {
        _playbackPosition.value = position
    }

    companion object {
        const val KEY_POSITION = "position"
    }
}
