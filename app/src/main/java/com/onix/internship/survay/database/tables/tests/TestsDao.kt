package com.onix.internship.survay.database.tables.tests

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TestsDao {
    @Insert
    suspend fun insert(tests: Tests)

    @Update
    suspend fun update(tests: Tests)

    @Query("SELECT * FROM TESTS")
    suspend fun getAllTests(): List<Tests>

    @Query("DELETE FROM TESTS")
    suspend fun clear()
}