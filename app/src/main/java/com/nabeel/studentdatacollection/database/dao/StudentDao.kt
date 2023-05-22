package com.nabeel.studentdatacollection.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.nabeel.studentdatacollection.database.entity.StudentInfo

@Dao
interface StudentDao {
    @Insert
    suspend fun insertStudentInfo(studentInfo: StudentInfo) : Long

    @Query("select * from student_info order by local_id desc")
    fun getAllData(): LiveData<List<StudentInfo>>
}