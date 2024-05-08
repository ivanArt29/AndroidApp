package com.example.prj3.TestBd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.prj3.viewModels.TestExViewModel
import com.example.prj3.viewModels.TestInnerViewModel

class StagesListViewModelFactory(private val repository: Repository): ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TestInnerViewModel(repository) as T
    }
}