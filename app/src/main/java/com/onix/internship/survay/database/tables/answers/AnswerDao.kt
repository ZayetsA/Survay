package com.onix.internship.survay.database.tables.answers

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface AnswerDao {
    @Insert
    suspend fun insert(answer: Answer)

    @Update
    suspend fun update(answer: Answer)

    @Query("SELECT * FROM ANSWERS WHERE answerQuestionId = :questionId")
    suspend fun getAnswersByQuestion(questionId: Int): List<Answer>

}