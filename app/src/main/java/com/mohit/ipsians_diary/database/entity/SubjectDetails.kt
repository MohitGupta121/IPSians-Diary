package com.mohit.ipsians_diary.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SubjectDetails(
    val subjectName: String,
    val attended: Int,
    val missed: Int
) {
    @PrimaryKey( autoGenerate = true )
    var id:Int = 0
}