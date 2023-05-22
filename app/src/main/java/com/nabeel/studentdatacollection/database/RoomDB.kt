package com.nabeel.studentdatacollection.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nabeel.studentdatacollection.database.dao.StudentDao
import com.nabeel.studentdatacollection.database.entity.StudentInfo

@Database(entities = [StudentInfo::class], version = 1)
abstract class RoomDB: RoomDatabase() {

    abstract fun studentDao(): StudentDao

    companion object{
        @Volatile
        private var INSTANCE: RoomDB ?= null

        fun getDatabase(context: Context): RoomDB{
            if(INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        RoomDB::class.java,
                        "student.db").build()
                }
            }
            return INSTANCE !!
        }
    }
}