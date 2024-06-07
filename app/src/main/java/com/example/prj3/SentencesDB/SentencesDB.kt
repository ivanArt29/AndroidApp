package com.example.prj3.SentencesDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.prj3.TestBd.TestDB
import com.example.prj3.TestBd.TestDao

@Database(entities = [Sentences::class, SentencesChoice::class], version = 1, exportSchema = true)
abstract class SentencesDB: RoomDatabase() {

    abstract fun sentDao(): SentencesDao

    companion object {

        private var INSTANCE: SentencesDB? = null

        fun getInstance(context: Context): SentencesDB? {
            if (INSTANCE == null){
                synchronized(SentencesDB::class){
                    INSTANCE = Room.databaseBuilder(
                        context,
                        SentencesDB::class.java, name = "SentencesDB"
                    ).createFromAsset("database/SentencesDB.db").build()
                }
            }
            return INSTANCE
        }
    }
}

