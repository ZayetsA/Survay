package com.onix.internship.survay.database

class RegisterRepository(private val dao: DataBaseDao) {
    val allUsers = dao.getAllUsers()
    val allUsersSize = dao.getAllUsers().value?.size
    val usersAndMentors = dao.getUsersAndMentors()


    suspend fun insert(user: User) {
        return dao.insert(user)
    }

    suspend fun getUserName(login: String): User? {
        return dao.getLogin(login)
    }

    suspend fun getNumUsers(): Int {
        return dao.getCount()
    }
}