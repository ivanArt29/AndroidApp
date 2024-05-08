package com.example.prj3.TestBd

import android.provider.UserDictionary.Words
import androidx.room.ColumnInfo
import androidx.room.DatabaseView
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "levels")
data class Levels (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "completed")
    val isCompleted: Boolean
)

@Entity(tableName = "stages")
data class Stages(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "parentLevelId")
    val parentLevelId: Int,
)

@Entity(tableName = "MyWords")
data class MyWords(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "word")
    val word: String,
    @ColumnInfo(name = "parentStageId")
    val parentStageId: Int,
    @ColumnInfo(name = "LevelId")
    val levelId: Int
)

data class levelWithStages(
    @Embedded
    val parentLevel:Levels,
    @Relation(
        parentColumn = "id",
        entityColumn = "parentLevelId"
    )
    val stages: List<Stages>
)

data class WordsWithStages(
    @Embedded
    val parentStage:Stages,
    @Relation(
        parentColumn = "id",
        entityColumn = "parentStageId"
    )
    val words: List<MyWords>
)
data class RuWordsWithStages(
    @Embedded
    val parentStage:Stages,
    @Relation(
        parentColumn = "id",
        entityColumn = "parentStageId"
    )
    val words: List<RuWords>
)

@Entity(tableName = "RuWords")
data class RuWords(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "ruWord")
    val ruWord: String,
    @ColumnInfo(name = "parentStageId")
    val parentStageId: Int,
    @ColumnInfo(name = "LevelId")
    val levelId: Int
)





