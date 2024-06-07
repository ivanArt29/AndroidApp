package com.example.prj3.SentencesDB

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction



@Dao
interface SentencesDao {
    @Transaction
    @Query("SELECT * FROM sentences WHERE levelId = :levelId AND stageId =:stageId")
    fun getSentenceForStage(levelId: Int, stageId: Int):List<Sentences>

    @Transaction
    @Query("SELECT * FROM sentencesChoice WHERE levelId = :levelId AND stageId =:stageId")
    fun getSentencesChoicesForStage(levelId: Int, stageId: Int):List<SentencesChoice>

    @Transaction
    @Query("SELECT * FROM sentencesChoice WHERE levelId = :levelId AND stageId = :stageId AND isCorrect = 1")
    fun getCorrectSentenceForStageByLevel(levelId: Int, stageId: Int): SentencesChoice
}