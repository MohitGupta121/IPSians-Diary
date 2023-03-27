package com.mohit.ipsians_diary.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mohit.ipsians_diary.database.entity.SaturdayEntity

@Dao
interface SaturdayDao {

    @Insert
     suspend fun add(sub : SaturdayEntity)

    @Query("SELECT * FROM SaturdayEntity ORDER BY startTime ASC")
    fun getSatClasses(): LiveData<List<SaturdayEntity>>

    @Query("DELETE FROM SaturdayEntity WHERE id=:uid")
    suspend fun delete(uid:Int)

}