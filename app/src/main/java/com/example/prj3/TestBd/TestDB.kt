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
                    ).build()
                }
            }
            return INSTANCE
        }

        val PREPOPULATE_DATA = listOf<Levels>(
            Levels(
                1,
                true
            ),
            Levels(
                2,
                true
            ),
            Levels(
                3,
                false
            )
        )

        val firstLevelWords = listOf<List<String>>(
            listOf<String>("apple","peach","pineapple","orange"),
            listOf<String>("dog","cat","squirrel","monkey"),
            listOf<String>("car","bus","bicycle","motorcycle"),
            listOf<String>("oven","microwave","stove","fridge")
        )
        val PREPOPULATE_STAGES = listOf<Stages>(
            Stages(
                1,
                1
//                firstLevelWords[0],
//                "Яблоко"
            ),
            Stages(
                2,
                1,
//                firstLevelWords[1],
//                "Кот"
            ),
            Stages(
                3,
                1,
//                firstLevelWords[2],
//                "Велосипед"
            ),
            Stages(
                4,
                1,
//                firstLevelWords[3],
//                "Холодильник"
            ),
        )
        val PREPOPULATE_WORDS = listOf<MyWords>(
            MyWords(
                0,"apple",1,1
            ),
            MyWords(
                1,"peach",1,1
            ),
            MyWords(
                2,"pineapple",1,1
            ),
            MyWords(
                3,"banana",1,1
            ),

            MyWords(
                0,"dog",2,1
            ),
            MyWords(
                1,"cat",2,1
            ),
            MyWords(
                2,"squirrel",2,1
            ),
            MyWords(
                3,"monkey",2,1
            ),

            MyWords(
                0,"car",3,1
            ),
            MyWords(
                1,"bus",3,1
            ),
            MyWords(
                2,"bicycle",3,1
            ),
            MyWords(
                3,"motorcycle",3,1
            ),

            MyWords(
                0,"oven",4,1
            ),
            MyWords(
                1,"microwave",4,1
            ),
            MyWords(
                2,"stove",4,1
            ),
            MyWords(
                3,"fridge",4,1
            )
        )

        val PREPOPULATE_RUWORDS = listOf<RuWords>(
            RuWords(
                0,"Яблоко",1,1
            ),
            RuWords(
                1,"Персик",2,1
            ),
            RuWords(
                2,"Ананас",3,1
            ),
            RuWords(
                3,"Банан",4,1
            )
        )

    }
}

