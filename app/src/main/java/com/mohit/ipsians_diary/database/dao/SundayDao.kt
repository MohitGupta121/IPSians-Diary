package com.mohit.ipsians_diary.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mohit.ipsians_diary.database.entity.SundayEntity

@Dao
interface SundayDao {

    @Insert
     suspend fun add(sub : SundayEntity)

    @Query("SELECT * FROM SundayEntity ORDER BY startTime ASC")
    fun getSunClasses(): LiveData<List<SundayEntity>>

    @Query("DELETE FROM SundayEntity WHERE id=:uid")
    suspend fun delete(uid:Int)

}