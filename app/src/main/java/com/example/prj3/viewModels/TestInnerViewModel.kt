package com.example.prj3.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prj3.TestBd.MyWords
import com.example.prj3.TestBd.Repository
import com.example.prj3.TestBd.RuWords
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TestInnerViewModel(private val repository: Repository) : ViewModel() {
    private val _wordsForStage = MutableLiveData<List<MyWords>?>()
    val wordsForStage: MutableLiveData<List<MyWords>?> = _wordsForStage

    private val _ruWordsForStage = MutableLiveData<List<RuWords>?>()
    val ruWordsForStage: MutableLiveData<List<RuWords>?> = _ruWordsForStage

    fun getWordsForStage(levelId: Int,stageId: Int) {
        viewModelScope.launch(Dispatchers.IO) { // Запускаем в фоновом потоке
            val stagesWithWords = repository.getStagesWithWordsForLevel(levelId)
            val wordsForStage = stagesWithWords.find { it.parentStage.id == stageId }?.words
            withContext(Dispatchers.Main) { // Возвращаемся в основной поток
                _wordsForStage.value = wordsForStage
            }
        }
    }

    fun getRuWordForStage(levelId: Int,stageId: Int) {
        viewModelScope.launch(Dispatchers.IO) { // Запускаем в фоновом потоке
            val stagesWithRuWords = repository.getStagesWithRuWords(levelId)
            val ruWordsForStage = stagesWithRuWords.find { it.parentStage.id == stageId }?.words
            withContext(Dispatchers.Main) { // Возвращаемся в основной поток
                _ruWordsForStage.value = ruWordsForStage
            }
        }
    }
}