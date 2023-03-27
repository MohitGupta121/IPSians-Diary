package com.mohit.ipsians_diary.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mohit.ipsians_diary.database.entity.SubjectDetails

@Dao
interface SubjectDao {

    @Insert
    suspend fun add(subject: SubjectDetails)

    @Update
    suspend fun update(subject: SubjectDetails)

    @Query("DELETE FROM SubjectDetails WHERE id=:uid")
    suspend fun delete(uid:Int)

    @Query("SELECT * FROM SubjectDetails")
    fun getAttendance(): LiveData<List<SubjectDetails>>

    @Query("SELECT SUM(attended) FROM SubjectDetails")
    fun getAttended():LiveData<Int>

    @Query("SELECT SUM(missed) FROM SubjectDetails")
    fun getMissed():LiveData<Int>

    @Query("SELECT subjectName FROM SubjectDetails")
    fun getSubjects():LiveData<List<String>>
}