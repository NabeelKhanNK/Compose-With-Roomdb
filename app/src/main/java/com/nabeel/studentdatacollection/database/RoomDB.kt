package com.nabeel.studentdatacollection.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.nabeel.studentdatacollection.database.dao.StudentDao
import com.nabeel.studentdatacollection.database.entity.StudentInfo

@Database(entities = [StudentInfo::class], version = 2)
abstract class RoomDB: RoomDatabase() {

    abstract fun studentDao(): StudentDao

    companion object{

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("Alter table student_info Add column flag TEXT Not Null Default '1'")
            }
        }

        @Volatile
        private var INSTANCE: RoomDB ?= null

        fun getDatabase(context: Context): RoomDB{
            if(INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        RoomDB::class.java,
                        "student.db")
                        .addMigrations(MIGRATION_1_2)
                        .build()
                }
            }
            return INSTANCE !!
        }
    }
}