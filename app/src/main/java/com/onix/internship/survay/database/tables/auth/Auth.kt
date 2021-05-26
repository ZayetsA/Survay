package com.onix.internship.survay.database.tables.auth

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "auth")
data class Auth(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "authId")
    var authId: Int = 0,

    @ColumnInfo(name = "authUserId")
    var authUserId: Long = 0,

    @ColumnInfo(name = "timeStamp")
    var timeStamp: Long = 0L
)