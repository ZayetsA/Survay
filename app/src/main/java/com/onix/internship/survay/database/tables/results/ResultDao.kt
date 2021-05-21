package com.onix.internship.survay.database.tables.results

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface ResultDao {
    @Insert
    suspend fun insert(result: Result)

    @Update
    suspend fun update(result: Result)

    @Query("SELECT * FROM results WHERE resultUserId =:userId AND resultTestId = :testId")
    suspend fun getResult(userId: Int, testId: Int): List<Result>

    @Query("SELECT * FROM results WHERE resultId = (SELECT MAX(resultId) FROM RESULTS)")
    suspend fun getLatestResult(): List<Result>

    @Query("SELECT * FROM results")
    suspend fun getAllResults(): List<Result>
}