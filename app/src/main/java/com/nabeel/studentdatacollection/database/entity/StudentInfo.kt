package com.nabeel.studentdatacollection.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Entity(tableName = "student_info")
data class StudentInfo(
    @PrimaryKey(autoGenerate = true)
    val local_id: Long,
    val name: String,
    val gender: String,
    val in_class: String,
    val subjects: String,
    val flag: String
)
