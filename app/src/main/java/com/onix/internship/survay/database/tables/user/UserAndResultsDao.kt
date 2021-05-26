package com.onix.internship.survay.database.tables.user

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface UserAndResultsDao {
    @Transaction
    @Query("SELECT * FROM users")
    fun getUsersWithResults(): List<UserAndResults>
}