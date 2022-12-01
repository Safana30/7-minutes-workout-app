package com.example.a7minutesworkout

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [HistoryEntity::class], version = 1)
abstract class HistoryDatabase :RoomDatabase(){


    abstract fun historyDao():HistoryDao

    companion object{

        @Volatile
        private var INSTANCE:HistoryDatabase?=null

        fun getDatabase(context: Context):HistoryDatabase{
            if(INSTANCE==null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        HistoryDatabase::class.java,
                        "History_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}