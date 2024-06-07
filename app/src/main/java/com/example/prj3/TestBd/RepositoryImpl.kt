package com.example.prj3.TestBd

import android.content.Context
import androidx.room.Dao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class RepositoryImpl(context: Context,private val backgroundDispatcher: CoroutineDispatcher): Repository {
    private val levelDao: TestDao

    init {
        val database = TestDB.getInstance(context)
        levelDao = database!!.levelDao()
    }
    //Levels
    override fun getAllLevels(): Flow<List<Levels>> {
        return levelDao.getLevels()
    }

    override suspend fun insert(level: Levels) {
        withContext(backgroundDispatcher){
            levelDao.insertLevel(level)
        }
    }

    override suspend fun update(level: Levels) {
        withContext(backgroundDispatcher){
            levelDao.updateLevel(level)
        }
    }

    override suspend fun delete(level: Levels) {
        withContext(backgroundDispatcher){
            levelDao.deleteLevel(level)
        }
    }



    //Stages
    override fun getAllStages(): Flow<List<Stages>> {
        return levelDao.getStages()
    }

    override suspend fun insertStage(stage: Stages) {
        withContext(backgroundDispatcher){
            levelDao.insertStage(stage)
        }
    }

    override suspend fun updateStage(stage: Stages) {
        withContext(backgroundDispatcher){
            levelDao.updateStage(stage)
        }
    }

    override suspend fun deleteStage(stage: Stages) {
        withContext(backgroundDispatcher){
            levelDao.deleteStage(stage)
        }
    }

    //Words
    override fun getAllWords(): Flow<List<MyWords>> {
        return levelDao.getWords()
    }

    override suspend fun insertWord(word: MyWords) {
        withContext(backgroundDispatcher){
            levelDao.insertWord(word)
        }
    }

    override suspend fun updateWord(word: MyWords) {
        withContext(backgroundDispatcher){
            levelDao.updateWord(word)
        }
    }

    override suspend fun deleteWord(word: MyWords) {
        withContext(backgroundDispatcher){
            levelDao.deleteWord(word)
        }
    }

    override fun getWordsForStageByLevel(levelId: Int, stagId: Int): List<MyWords> {
        return levelDao.getWordsForStageByLevel(levelId,stagId)
    }

    override fun getCorrectWordForStageByLevel(levelId: Int, stagId: Int): MyWords {
        return levelDao.getCorrectWordForStageByLevel(levelId,stagId)
    }

    override fun getStagesWithWordsForLevel(levelId: Int): List<WordsWithStages> {
        return levelDao.getStagesWithWordsForLevel(levelId)
    }


    //RuWords
    override fun getRuWords(levelId: Int, stagId: Int): List<RuWords> {
        return levelDao.getRuWords(levelId, stagId)
    }

    override suspend fun insertRuWord(ruWord: RuWords) {
        withContext(backgroundDispatcher){
            levelDao.insertRuWord(ruWord)
        }
    }

    override suspend fun updateRuWord(ruWord: RuWords) {
        withContext(backgroundDispatcher){
            levelDao.updateRuWord(ruWord)
        }
    }

    override suspend fun deleteRuWord(ruWord: RuWords) {
        withContext(backgroundDispatcher){
            levelDao.deleteRuWord(ruWord)
        }
    }

    override fun getStagesWithRuWords(levelId: Int): List<RuWordsWithStages> {
        return levelDao.getStagesWithRuWords(levelId)
    }


}