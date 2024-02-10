package com.disney.codingexercise.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.disney.codingexercise.model.downloadmetadata.DownloadMetaDataRepository
import com.disney.codingexercise.model.downloadmetadata.DownloadMetadata
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// Example of how a Compose ViewModel might inject and use our repository.
class SampleViewModel(private val repository: DownloadMetaDataRepository): ViewModel() {
    private var downloadMetadata: List<DownloadMetadata> by mutableStateOf(listOf())

    fun getDownloadMetadata(ids: List<String>) {
        viewModelScope.launch(Dispatchers.IO) {
            downloadMetadata = repository.getDownloadMetadata(ids)
        }
    }

    fun storeDownloadMetadata(metadata: DownloadMetadata) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.storeDownloadMetadata(metadata)
        }
    }
}