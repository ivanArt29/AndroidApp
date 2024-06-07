package com.example.prj3.SentencesDB
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.prj3.viewModels.SentencesInnerViewModel

class SentencesStagesListViewModelFactory(private val repository: RepositorySentences): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SentencesInnerViewModel(repository) as T
    }
}