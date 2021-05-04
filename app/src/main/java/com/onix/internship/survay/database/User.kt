package com.onix.internship.survay.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(

    @PrimaryKey(autoGenerate = true)
    var userId: Int = 0,

    @ColumnInfo(name = "first_name")
    var firstName: String,

    @ColumnInfo(name = "last_name")
    var lastName: String,

    @ColumnInfo(name = "login")
    var login: String,

    @ColumnInfo(name = "password")
    var password: String,

    @ColumnInfo(name = "role")
    var role: Int
)