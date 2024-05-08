package com.example.prj3.TestBd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.prj3.viewModels.TestExViewModel

class LevelsListViewModelFactory (private val repository: Repository):ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TestExViewModel(repository) as T
    }
}