package com.example.prj3.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prj3.AudioDB.Audio
import com.example.prj3.AudioDB.RepositoryAudio
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class AudioInnerViewModel(private val repository: RepositoryAudio) : ViewModel() {

    private val _audioForStagePath = MutableLiveData<List<Audio>?>()
    val audioForStagePath: MutableLiveData<List<Audio>?> = _audioForStagePath

    private val _correctCount = MutableLiveData<Int>().apply { value = 0 }
    val correctCount: LiveData<Int> = _correctCount

    private val _correctAudioForStage = MutableLiveData<String>()
    val correctAudioForStage: MutableLiveData<String> = _correctAudioForStage

    var cor = ""


    fun incrementCorrectCount() {
        _correctCount.value = (_correctCount.value ?: 0) + 1
    }


    fun getAudioPathForStage(levelId: Int,stageId: Int) {
        viewModelScope.launch(Dispatchers.IO) { // Запускаем в фоновом потоке
            val audioPathForStage = repository.getAudioForStage(levelId,stageId)
            withContext(Dispatchers.Main) { // Возвращаемся в основной поток
                _audioForStagePath.value = audioPathForStage
            }
        }
    }

//    fun getEmptySentenceForStage(levelId: Int,stageId: Int) {
//        viewModelScope.launch(Dispatchers.IO) { // Запускаем в фоновом потоке
//            val emptySentenceForStage = repository.getSentenceForStage(levelId,stageId)
//            withContext(Dispatchers.Main) { // Возвращаемся в основной поток
//                _emptySentenceForStage.value = emptySentenceForStage
//            }
//        }
//    }



    fun getCorrectAudio(levelId: Int, stageId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val correctAudio = repository.getCorrectAudio(levelId, stageId).text
            withContext(Dispatchers.Main) {
                _correctAudioForStage.value = correctAudio
                cor = correctAudio
            }
        }
    }

    fun getCorrectValue(): String {
        return cor
    }
}