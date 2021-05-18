package com.onix.internship.survay.database.tables.tests

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TestsDao {
    @Insert
    suspend fun insert(test: Tests)

    @Update
    suspend fun update(test: Tests)

    @Query("SELECT * FROM TESTS")
    suspend fun getAllTests(): List<Tests>

    @Query("DELETE FROM TESTS")
    suspend fun clear()
}