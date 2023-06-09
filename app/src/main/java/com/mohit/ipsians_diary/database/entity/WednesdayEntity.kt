package com.mohit.ipsians_diary.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class WednesdayEntity(subjectName: String, startTime: String, startTimeShow: String, endTime: String, endTimeShow: String, roomNumber: String) :
        TimetableEntity(subjectName, startTime, startTimeShow, endTime, endTimeShow, roomNumber) {
    @PrimaryKey( autoGenerate = true )
    var id:Int = 0
}