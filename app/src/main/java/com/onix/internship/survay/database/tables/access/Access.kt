package com.onix.internship.survay.database.tables.access

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "access")
data class Access(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "accessId")
    var accessId: Int = 0,

    @ColumnInfo(name = "accessUserId")
    var userId: Int = 0,

    @ColumnInfo(name = "accessTestId")
    var testId: Int = 0,

    @ColumnInfo(name = "enable")
    var enable: Boolean = false
)