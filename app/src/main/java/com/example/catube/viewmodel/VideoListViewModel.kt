package com.example.catube.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catube.model.Video
import com.example.catube.model.api.AnswerApi
import com.example.catube.model.api.ErrorAnswerApi
import com.example.catube.model.api.PendingAnswerApi
import com.example.catube.model.api.RetrofitClient
import com.example.catube.model.api.SuccessAnswerApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VideoListViewModel(
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _videoData = savedStateHandle.getLiveData<AnswerApi<List<Video>>>(KEY_STATE)
    val videoData: LiveData<AnswerApi<List<Video>>> = _videoData

    fun fetchData() {
        _videoData.postValue(PendingAnswerApi())
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    RetrofitClient.apiService.watchTV()
                }
                _videoData.postValue(SuccessAnswerApi(response.results))
            }
            catch (e: Exception) {
                _videoData.postValue(ErrorAnswerApi(e))
            }
        }
    }

    companion object {
        const val KEY_STATE = "state"
    }
}
