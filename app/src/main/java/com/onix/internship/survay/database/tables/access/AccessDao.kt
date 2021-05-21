package com.onix.internship.survay.database.tables.access

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface AccessDao {
    @Insert
    suspend fun insert(access: Access)

    @Update
    suspend fun update(access: Access)

    @Query("SELECT * FROM access WHERE accessUserId = :userId AND accessTestId = :testId")
    suspend fun getAccess(userId: Int, testId: Int): List<Access>
}