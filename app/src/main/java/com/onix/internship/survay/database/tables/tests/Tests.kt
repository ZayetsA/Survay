package com.onix.internship.survay.database.tables.tests

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "tests")
data class Tests(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "testId")
    var testId: Int = 0,

    @ColumnInfo(name = "testName")
    var testName: String = "",

    @ColumnInfo(name = "testDescription")
    var testDescriptor: String = ""
) : Parcelable