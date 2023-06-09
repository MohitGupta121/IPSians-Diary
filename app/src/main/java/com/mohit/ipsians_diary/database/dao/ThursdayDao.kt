package com.mohit.ipsians_diary.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mohit.ipsians_diary.database.entity.ThursdayEntity

@Dao
interface ThursdayDao {

    @Insert
     suspend fun add(sub : ThursdayEntity)

    @Query("SELECT * FROM ThursdayEntity ORDER BY startTime ASC")
    fun getThursClasses(): LiveData<List<ThursdayEntity>>

    @Query("DELETE FROM ThursdayEntity WHERE id=:uid")
    suspend fun delete(uid:Int)

}