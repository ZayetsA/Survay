package com.onix.internship.survay.database.tables.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.onix.internship.survay.util.UserRoleStates


@Dao
interface UserDao {
    @Insert
    suspend fun insert(register: User): Long

    @Update
    suspend fun update(user: User)

    @Query("SELECT * FROM users WHERE role = :user OR role = :manager")
    suspend fun getUsersAndMentors(user: UserRoleStates, manager: UserRoleStates): List<User>

    @Query("SELECT * from users WHERE login = :login AND password = :password")
    suspend fun get(login: String, password: String): List<User>

    @Query("SELECT * FROM users WHERE login LIKE :login")
    suspend fun getLogin(login: String): User?

    @Query("SELECT COUNT(*) FROM USERS")
    suspend fun getCount(): Int
}