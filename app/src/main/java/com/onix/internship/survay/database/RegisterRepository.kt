package com.onix.internship.survay.database

class RegisterRepository(private val dao: DataBaseDao) {
    val users = dao.getAllUsers()
    suspend fun insert(user: User) {
        return dao.insert(user)
    }

    suspend fun getUserName(login: String): User? {
        return dao.getLogin(login)
    }
}