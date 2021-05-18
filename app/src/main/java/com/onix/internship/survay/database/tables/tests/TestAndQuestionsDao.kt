package com.onix.internship.survay.database.tables.tests

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface TestAndQuestionsDao {
    @Transaction
    @Query("SELECT * FROM tests")
    fun getTestWithQuestions(): List<TestAndQuestions>
}