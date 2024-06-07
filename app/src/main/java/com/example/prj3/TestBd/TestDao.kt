package com.example.prj3.TestBd

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TestDao {

    //Level
    @Query("SELECT * FROM levels ORDER BY id DESC")
    fun getLevels(): Flow<List<Levels>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertLevel(level: Levels): Long

    @Update
    suspend fun updateLevel(level: Levels): Int

    @Delete
    suspend fun deleteLevel(level: Levels): Int


    //Stages
    @Query("SELECT * FROM stages ORDER BY id DESC")
    fun getStages(): Flow<List<Stages>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStage(stage: Stages): Long

    @Update
    suspend fun updateStage(stage: Stages): Int

    @Delete
    suspend fun deleteStage(stage: Stages): Int

//    @Transaction
//    @Query("SELECT * FROM levels ORDER BY id DESC")
//    suspend fun getLevelsWithStages():List<levelWithStages>
//
//    @Transaction
//    @Query("SELECT * FROM levels WHERE id=:id ORDER BY id DESC")
//    suspend fun getLevelById(id:Long):List<levelWithStages>
@Transaction
@Query("SELECT * FROM levels ORDER BY id DESC")
fun getLevelsWithStages(): Flow<List<levelWithStages>>

    @Transaction
    @Query("SELECT * FROM levels WHERE id = :id ORDER BY id DESC")
    fun getLevelWithStagesById(id: Int): Flow<levelWithStages>


    //Words
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWord(word: MyWords)

    @Query("SELECT * FROM mywords ORDER BY id DESC")
    fun getWords(): Flow<List<MyWords>>

    @Update
    suspend fun updateWord(word: MyWords): Int

    @Delete
    suspend fun deleteWord(word: MyWords): Int

    @Transaction
    @Query("SELECT * FROM stages ORDER BY id DESC")
    suspend fun getStagesWithWords(): List<WordsWithStages>

    @Transaction
    @Query("SELECT * FROM stages WHERE id = :id ORDER BY id DESC")
    suspend fun getStagesWithWords(id: Int): List<WordsWithStages>


    @Transaction
    @Query("SELECT * FROM stages WHERE parentLevelId = :levelId")
    fun getStagesWithWordsForLevel(levelId: Int): List<WordsWithStages>

    @Transaction
    @Query("SELECT * FROM mywords WHERE levelId = :levelId AND parentStageId = :stagId")
    fun getWordsForStageByLevel(levelId: Int, stagId: Int):List<MyWords>

    @Transaction
    @Query("SELECT * FROM mywords WHERE levelId = :levelId AND parentStageId = :stagId AND isCorrect = 1")
    fun getCorrectWordForStageByLevel(levelId: Int, stagId: Int):MyWords


    //RuWords
    @Query("SELECT * FROM RuWords WHERE levelId = :levelId AND parentStageId = :stagId")
    fun getRuWords(levelId: Int, stagId: Int): List<RuWords>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRuWord(ruWord: RuWords): Long

    @Update
    suspend fun updateRuWord(ruWord: RuWords): Int

    @Delete
    suspend fun deleteRuWord(ruWord: RuWords): Int

    @Transaction
    @Query("SELECT * FROM stages WHERE parentLevelId = :levelId")
    fun getStagesWithRuWords(levelId: Int): List<RuWordsWithStages>


}