package com.onix.internship.survay.database.tables.results

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "results")
data class Result(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "resultId")
    var resultId: Int = 0,

    @ColumnInfo(name = "resultUserId")
    var resultUserId: Int = 0,

    @ColumnInfo(name = "date")
    var date: Long = Calendar.getInstance().timeInMillis,

    @ColumnInfo(name = "resultTestId")
    var resultTestId: Int = 0,

    @ColumnInfo(name = "score")
    var score: Int = 0
)