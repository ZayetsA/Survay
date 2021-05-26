package com.onix.internship.survay.database.tables.questions

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface QuestionAndAnswerDao {
    @Transaction
    @Query("SELECT * FROM questions WHERE questionTestId = :testId")
    fun getQuestionsAndAnswers(testId: Int): List<QuestionAndAnswer>
}