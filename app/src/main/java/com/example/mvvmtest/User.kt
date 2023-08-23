package com.example.mvvmtest

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    var uid: Int,

    @ColumnInfo("name")
    var name: String? = "",

    @ColumnInfo("age")
    var age: String? =""
)