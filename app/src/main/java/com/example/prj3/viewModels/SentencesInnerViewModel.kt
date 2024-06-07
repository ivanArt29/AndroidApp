package com.example.prj3.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prj3.SentencesDB.RepositorySentences
import com.example.prj3.TestBd.MyWords
import com.example.prj3.TestBd.RuWords
import com.example.prj3.SentencesDB.Sentences
import com.example.prj3.SentencesDB.SentencesChoice
import com.example.prj3.TestBd.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SentencesInnerViewModel(private val repository: RepositorySentences) : ViewModel() {
    private val _sentencesForStage = MutableLiveData<List<SentencesChoice>?>()
    val sentencesForStage: MutableLiveData<List<SentencesChoice>?> = _sentencesForStage

    private val _emptySentenceForStage = MutableLiveData<List<Sentences>?>()
    val emptySentenceForStage: MutableLiveData<List<Sentences>?> = _emptySentenceForStage

    private val _correctCount = MutableLiveData<Int>().apply { value = 0 }
    val correctCount: LiveData<Int> = _correctCount

    private val _correctSentenceForStage = MutableLiveData<String>()
    val correctSentenceForStage: MutableLiveData<String> = _correctSentenceForStage

    fun incrementCorrectCount() {
        _correctCount.value = (_correctCount.value ?: 0) + 1
    }


    fun getSentencesForStage(levelId: Int,stageId: Int) {
        viewModelScope.launch(Dispatchers.IO) { // Запускаем в фоновом потоке
            val sentencesForStage = repository.getSentencesChoicesForStage(levelId,stageId)
            withContext(Dispatchers.Main) { // Возвращаемся в основной поток
                _sentencesForStage.value = sentencesForStage
            }
        }
    }

    fun getEmptySentenceForStage(levelId: Int,stageId: Int) {
        viewModelScope.launch(Dispatchers.IO) { // Запускаем в фоновом потоке
            val emptySentenceForStage = repository.getSentenceForStage(levelId,stageId)
            withContext(Dispatchers.Main) { // Возвращаемся в основной поток
                _emptySentenceForStage.value = emptySentenceForStage
            }
        }
    }

    fun getCorrectSentence(levelId: Int, stageId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val correctSentence = repository.getCorrectSentenceForStageByLevel(levelId, stageId).text
            withContext(Dispatchers.Main) {
                _correctSentenceForStage.value = correctSentence
            }
        }
    }
}