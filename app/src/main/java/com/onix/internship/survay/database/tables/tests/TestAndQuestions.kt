package com.onix.internship.survay.database.tables.tests

import androidx.room.Embedded
import androidx.room.Relation
import com.onix.internship.survay.database.tables.questions.Question

class TestAndQuestions(
    @Embedded val tests: Tests,
    @Relation(
        parentColumn = "testId",
        entityColumn = "questionId"
    )
    val listOfTests: List<Question>
)