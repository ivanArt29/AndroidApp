package com.example.prj3.AudioDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Audio")
data class Audio (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "path")
    val path: String,
    @ColumnInfo(name = "stageId")
    val stageId : Int,
    @ColumnInfo(name = "levelId")
    val levelId : Int
)

@Entity(tableName = "CorrectAudio")
data class CorrectAudio (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "text")
    val text: String,
    @ColumnInfo(name = "stageId")
    val stageId : Int,
    @ColumnInfo(name = "levelId")
    val levelId : Int
)
