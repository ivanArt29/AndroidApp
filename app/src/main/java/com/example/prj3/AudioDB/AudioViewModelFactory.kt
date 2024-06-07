package com.example.prj3.AudioDB

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.prj3.SentencesDB.RepositorySentences

import com.example.prj3.viewModels.AudioInnerViewModel


class AudioViewModelFactory(private val repository: RepositoryAudio): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AudioInnerViewModel(repository) as T
    }
}

