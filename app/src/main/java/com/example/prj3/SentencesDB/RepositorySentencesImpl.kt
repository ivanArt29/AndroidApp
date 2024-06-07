package com.example.prj3.SentencesDB
import android.content.Context
import kotlinx.coroutines.CoroutineDispatcher


class RepositorySentencesImpl (context: Context, private val backgroundDispatcher: CoroutineDispatcher): RepositorySentences {
    private val sentDao: SentencesDao

    init {
        val database = SentencesDB.getInstance(context)
        sentDao = database!!.sentDao()
    }

    override fun getSentenceForStage(levelId: Int, stageId: Int): List<Sentences> {
        return sentDao.getSentenceForStage(levelId,stageId)
    }

    override fun getSentencesChoicesForStage(levelId: Int, stageId: Int): List<SentencesChoice> {
        return sentDao.getSentencesChoicesForStage(levelId, stageId)
    }

    override fun getCorrectSentenceForStageByLevel(levelId: Int, stageId: Int): SentencesChoice {
        return sentDao.getCorrectSentenceForStageByLevel(levelId, stageId)
    }
}