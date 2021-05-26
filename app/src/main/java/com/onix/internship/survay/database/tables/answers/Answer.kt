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
    
    @ColumnInfo(name = "text")
    var text: String = ""
)