package com.example.catube.viewmodel.utils

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.catube.viewmodel.VideoListViewModel
import com.example.catube.viewmodel.VideoPlayerViewModel

class ViewModelFactory(
    owner: SavedStateRegistryOwner
): AbstractSavedStateViewModelFactory(owner, null) {
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        val viewModel = when(modelClass) {
            VideoListViewModel::class.java -> {
                VideoListViewModel(handle)
            }
            VideoPlayerViewModel::class.java -> {
                VideoPlayerViewModel(handle)
            }
            else -> throw IllegalStateException()
        }
        return viewModel as T
    }
}
