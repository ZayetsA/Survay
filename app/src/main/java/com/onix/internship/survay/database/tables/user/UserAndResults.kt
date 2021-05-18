package com.onix.internship.survay.database.tables.user

import androidx.room.Embedded
import androidx.room.Relation
import com.onix.internship.survay.database.tables.results.Result

class UserAndResults(

    @Embedded
    val user: User,

    @Relation(
        parentColumn = "userId",
        entityColumn = "resultId"
    )
    val playlists: List<Result>
)