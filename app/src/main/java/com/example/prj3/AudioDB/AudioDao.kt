package com.example.prj3.AudioDB

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface AudioDao {
    @Transaction
    @Query("SELECT * FROM Audio WHERE levelId = :levelId AND stageId =:stageId")
    fun getAudioForStage(levelId: Int, stageId: Int):List<Audio>

    @Transaction
    @Query("SELECT * FROM CorrectAudio WHERE levelId = :levelId AND stageId =:stageId")
    fun getCorrectAudio(levelId: Int, stageId: Int):CorrectAudio
}