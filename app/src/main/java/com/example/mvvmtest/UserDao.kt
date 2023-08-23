package com.example.mvvmtest

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao{
    @Query("select * from user")
    suspend fun getUserAll(): List<User>

    @Query("select * from user where uid like :id")
    suspend fun getUserById(id: String): User?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(vararg user: User)

    @Delete
    suspend fun deleteUser(user: User)
}