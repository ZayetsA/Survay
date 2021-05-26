package com.onix.internship.survay.database.tables.questions

import androidx.room.Embedded
import androidx.room.Relation
import com.onix.internship.survay.database.tables.answers.Answer

data class QuestionAndAnswer(
    @Embedded val question: Question,
    @Relation(
        parentColumn = "questionId",
        entityColumn = "answerQuestionId"
    )
    val listOfVariants: List<Answer>
)