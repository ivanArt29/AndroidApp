package com.example.prj3.AudioDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.prj3.SentencesDB.Sentences
import com.example.prj3.SentencesDB.SentencesChoice
import com.example.prj3.SentencesDB.SentencesDao

@Database(entities = [Audio::class,CorrectAudio::class], version = 1, exportSchema = true)
abstract class AudioDB: RoomDatabase() {

    abstract fun audioDao(): AudioDao

    companion object {

        private var INSTANCE: AudioDB? = null

        fun getInstance(context: Context): AudioDB? {
            if (INSTANCE == null){
                synchronized(AudioDB::class){
                    INSTANCE = Room.databaseBuilder(
                        context,
                        AudioDB::class.java, name = "AudioDB"
                    ).createFromAsset("database/AudioDb.db").build()
                }
            }
            return INSTANCE
        }
    }
}