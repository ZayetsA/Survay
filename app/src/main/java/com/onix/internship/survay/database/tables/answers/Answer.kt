package com.onix.internship.survay.database.tables.answers

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "answers")
data class Answer(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "answerId")
    var answerId: Int = 0,

    @ColumnInfo(name = "answerQuestionId")
    var answerQuestionId: Int = 0,

    @ColumnInfo(name = "score")
    var score: Int = 0,

    @ColumnInfo(name = "var1")
    var var1: String = "",

    @ColumnInfo(name = "var2")
    var var2: String = "",

    @ColumnInfo(name = "var3")
    var var3: String = "",

    @ColumnInfo(name = "var4")
    var var4: String = "",

    @ColumnInfo(name = "answer")
    var answer: String = ""
)