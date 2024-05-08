package com.example.prj3.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.prj3.TestBd.Levels
import com.example.prj3.TestBd.MyWords
import com.example.prj3.TestBd.Repository
import com.example.prj3.TestBd.RuWords
import com.example.prj3.TestBd.Stages
import com.example.prj3.TestBd.TestDB
import kotlinx.coroutines.launch

class TestExViewModel(private val repository: Repository) : ViewModel() {

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading:LiveData<Boolean> = _dataLoading

    init {
        for (level in TestDB.PREPOPULATE_DATA){
            viewModelScope.launch {
                repository.insert(level)
            }
        }
    }

    fun insertLevel(level: Levels)
    {
        showProgress()
        viewModelScope.launch {
            repository.insert(level)
        }
        hideProgress()
    }

    fun updateLevel(level: Levels)
    {
        showProgress()
        viewModelScope.launch {
            repository.update(level)
        }
        hideProgress()
    }

    fun deleteLevel(level: Levels)
    {
        showProgress()
        viewModelScope.launch {
            repository.delete(level)
        }
        hideProgress()
    }

    fun getAllLevels(): LiveData<List<Levels>>
    {
        val levels: LiveData<List<Levels>>?
        _dataLoading.value = true
        levels = repository.getAllLevels().asLiveData()
        _dataLoading.value = false
        return levels
    }

    fun insertStage(stage: Stages)
    {
        showProgress()
        viewModelScope.launch {
            repository.insertStage(stage)
        }
        hideProgress()
    }

    fun insertWord(word: MyWords)
    {
        showProgress()
        viewModelScope.launch {
            repository.insertWord(word)
        }
        hideProgress()
    }

    fun insertRuWord(ruWords: RuWords)
    {
        showProgress()
        viewModelScope.launch {
            repository.insertRuWord(ruWords)
        }
        hideProgress()
    }

    private fun showProgress(){
        _dataLoading.value = true
    }

    private fun hideProgress(){
        _dataLoading.value = false
    }

}