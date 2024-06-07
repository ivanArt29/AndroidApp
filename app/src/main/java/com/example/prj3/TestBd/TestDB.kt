package com.example.prj3.TestBd

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

const val DATABASE_VERSION_CODE = 1

@Database(entities = [Levels::class, Stages::class, MyWords::class, RuWords::class], version = DATABASE_VERSION_CODE, exportSchema = true)
abstract class TestDB : RoomDatabase() {

    abstract fun levelDao(): TestDao

    companion object {

        private var INSTANCE: TestDB? = null

        fun getInstance(context: Context): TestDB? {
            if (INSTANCE == null){
                synchronized(TestDB::class){
                    INSTANCE = Room.databaseBuilder(
                        context,
                        TestDB::class.java, name = "database"
                    ).createFromAsset("database/TestDB.db").build()
                }
            }
            return INSTANCE
        }
    }
}

