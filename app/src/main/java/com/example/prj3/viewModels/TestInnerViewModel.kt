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

    private val _correctCount = MutableLiveData<Int>().apply { value = 0 }
    val correctCount: LiveData<Int> = _correctCount

    private val _correctWordForStage = MutableLiveData<String>()
    val correctWordForStage: MutableLiveData<String> = _correctWordForStage

    fun incrementCorrectCount() {
        _correctCount.value = (_correctCount.value ?: 0) + 1
    }


    fun getWordsForStage(levelId: Int,stageId: Int) {
        viewModelScope.launch(Dispatchers.IO) { // Запускаем в фоновом потоке
            val wordsForStage = repository.getWordsForStageByLevel(levelId,stageId)
            withContext(Dispatchers.Main) { // Возвращаемся в основной поток
                _wordsForStage.value = wordsForStage
            }
        }
    }

    fun getRuWordForStage(levelId: Int,stageId: Int) {
        viewModelScope.launch(Dispatchers.IO) { // Запускаем в фоновом потоке
            val ruWordsForStage = repository.getRuWords(levelId,stageId)
            withContext(Dispatchers.Main) { // Возвращаемся в основной поток
                _ruWordsForStage.value = ruWordsForStage
            }
        }
    }

    fun getCorrectWord(levelId: Int, stageId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val correctWord = repository.getCorrectWordForStageByLevel(levelId, stageId).word
            withContext(Dispatchers.Main) {
                _correctWordForStage.value = correctWord
            }
        }
    }
}