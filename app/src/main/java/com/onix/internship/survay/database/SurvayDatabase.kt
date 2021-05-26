package com.onix.internship.survay.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.onix.internship.survay.database.tables.access.Access
import com.onix.internship.survay.database.tables.access.AccessDao
import com.onix.internship.survay.database.tables.answers.Answer
import com.onix.internship.survay.database.tables.answers.AnswerDao
import com.onix.internship.survay.database.tables.auth.Auth
import com.onix.internship.survay.database.tables.auth.AuthDao
import com.onix.internship.survay.database.tables.questions.Question
import com.onix.internship.survay.database.tables.questions.QuestionAndAnswerDao
import com.onix.internship.survay.database.tables.questions.QuestionDao
import com.onix.internship.survay.database.tables.results.Result
import com.onix.internship.survay.database.tables.results.ResultDao
import com.onix.internship.survay.database.tables.tests.TestAndQuestionsDao
import com.onix.internship.survay.database.tables.tests.Tests
import com.onix.internship.survay.database.tables.tests.TestsDao
import com.onix.internship.survay.database.tables.user.User
import com.onix.internship.survay.database.tables.user.UserAndResultsDao
import com.onix.internship.survay.database.tables.user.UserDao

@Database(
    entities = [Auth::class, User::class, Answer::class, Question::class, Result::class, Tests::class, Access::class],
    version = 1,
    exportSchema = false
)
abstract class SurvayDatabase : RoomDatabase() {
    abstract val userDatabaseDao: UserDao
    abstract val answerDao: AnswerDao
    abstract val questionDao: QuestionDao
    abstract val resultDao: ResultDao
    abstract val testsDao: TestsDao
    abstract val accessDao: AccessDao
    abstract val authDao: AuthDao
    abstract val userAndResultsDao: UserAndResultsDao
    abstract val testAndQuestionsDao: TestAndQuestionsDao
    abstract val questionAndAnswerDao: QuestionAndAnswerDao

    companion object {
        @Volatile
        private var INSTANCE: SurvayDatabase? = null

        fun getInstance(context: Context): SurvayDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SurvayDatabase::class.java,
                        "survay_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}