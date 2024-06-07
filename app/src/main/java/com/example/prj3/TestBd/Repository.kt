package com.example.prj3.TestBd

import kotlinx.coroutines.flow.Flow
import java.util.logging.Level

interface Repository{
    fun getAllLevels():Flow<List<Levels>>
    suspend fun insert(level:Levels)
    suspend fun update(level: Levels)
    suspend fun delete(level: Levels)

    fun getAllStages():Flow<List<Stages>>
    suspend fun insertStage(stage:Stages)
    suspend fun updateStage(stage:Stages)
    suspend fun deleteStage(stage:Stages)

    fun getAllWords():Flow<List<MyWords>>
    suspend fun insertWord(word:MyWords)
    suspend fun updateWord(word:MyWords)
    suspend fun deleteWord(word:MyWords)

    fun getWordsForStageByLevel(levelId: Int, stagId: Int):List<MyWords>

    fun getCorrectWordForStageByLevel(levelId: Int, stagId: Int):MyWords


    fun getStagesWithWordsForLevel(levelId: Int): List<WordsWithStages>

    fun getRuWords(levelId: Int, stagId: Int):List<RuWords>
    suspend fun insertRuWord(ruWord:RuWords)
    suspend fun updateRuWord(ruWord:RuWords)
    suspend fun deleteRuWord(ruWord:RuWords)

    fun getStagesWithRuWords(levelId: Int): List<RuWordsWithStages>
}