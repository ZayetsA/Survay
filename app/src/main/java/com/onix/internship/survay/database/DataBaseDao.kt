package com.onix.internship.survay.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface DataBaseDao {
    @Insert
    suspend fun insert(register: User)

    @Query("SELECT * FROM users ORDER BY userId DESC")
    suspend fun getAllUsers(): List<User>

    @Query("SELECT * FROM users WHERE role == 1 OR role == 2")
    suspend fun getUsersAndMentors(): List<User>

    @Query("SELECT * FROM users WHERE login LIKE :login")
    suspend fun getLogin(login: String): User?

    @Query("SELECT COUNT(*) FROM USERS")
    suspend fun getCount(): Int
}