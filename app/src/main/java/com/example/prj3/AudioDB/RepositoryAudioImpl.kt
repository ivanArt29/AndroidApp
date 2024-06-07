package com.example.prj3.AudioDB
import android.content.Context

import kotlinx.coroutines.CoroutineDispatcher

class RepositoryAudioImpl(context: Context, private val backgroundDispatcher: CoroutineDispatcher): RepositoryAudio {
    private val audioDao: AudioDao

    init {
        val database = AudioDB.getInstance(context)
        audioDao = database!!.audioDao()
    }

    override fun getAudioForStage(levelId: Int, stageId: Int): List<Audio> {
        return audioDao.getAudioForStage(levelId, stageId)
    }

    override fun getCorrectAudio(levelId: Int, stageId: Int): CorrectAudio {
        return audioDao.getCorrectAudio(levelId, stageId)
    }

}