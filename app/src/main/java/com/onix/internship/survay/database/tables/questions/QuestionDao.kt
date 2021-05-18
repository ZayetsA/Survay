package com.onix.internship.survay.database.tables.questions

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface QuestionDao {

    @Insert
    suspend fun insert(question: Question)

    @Update
    suspend fun update(question: Question)

    @Query("SELECT * FROM questions WHERE questionTestId = :testId")
    suspend fun getQuestionsByTest(testId: Int): List<Question>
}