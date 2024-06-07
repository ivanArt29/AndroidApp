package com.example.prj3.SentencesDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sentences")
data class Sentences (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "text")
    val text: String,
    @ColumnInfo(name = "stageId")
    val stageId : Int,
    @ColumnInfo(name = "levelId")
    val levelId : Int
)

@Entity(tableName = "sentencesChoice")
data class SentencesChoice (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "text")
    val text: String,
    @ColumnInfo(name = "isCorrect")
    val isCorrect: Int,
    @ColumnInfo(name = "stageId")
    val stageId : Int,
    @ColumnInfo(name = "levelId")
    val levelId : Int
)