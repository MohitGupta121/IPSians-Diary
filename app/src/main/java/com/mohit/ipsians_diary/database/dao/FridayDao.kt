package com.mohit.ipsians_diary.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mohit.ipsians_diary.database.entity.FridayEntity

@Dao
interface FridayDao {

    @Query("DELETE FROM FridayEntity WHERE id=:uid")
    suspend fun delete(uid:Int)

    @Insert
     suspend fun add(sub : FridayEntity)

    @Query("SELECT * FROM FridayEntity ORDER BY startTime ASC")
    fun getFriClasses(): LiveData<List<FridayEntity>>
}