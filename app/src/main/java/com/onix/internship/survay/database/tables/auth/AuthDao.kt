package com.onix.internship.survay.database.tables.auth

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AuthDao {
    @Insert
    suspend fun insert(auth: Auth)

    @Query("SELECT * FROM auth ORDER BY authId DESC LIMIT 1")
    suspend fun getCurrentUser(): List<AuthAndUser>
}