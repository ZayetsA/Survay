package com.onix.internship.survay.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface DataBaseDao {
    @Insert
    suspend fun insert(register: User)

    @Query("SELECT * FROM users ORDER BY userId DESC")
    fun getAllUsers(): LiveData<List<User>>

    @Query("SELECT * FROM users WHERE login LIKE :login")
    suspend fun getLogin(login: String): User?
}