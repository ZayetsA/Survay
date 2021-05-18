package com.onix.internship.survay.database.tables.questions

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
data class Question(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "questionId")
    var questionId: Int = 0,


    @ColumnInfo(name = "questionTestId")
    var testId: Int = 0,

    @ColumnInfo(name = "text")
    var text: String = ""
)