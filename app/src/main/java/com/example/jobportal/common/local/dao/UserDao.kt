package com.example.jobportal.common.local.dao

import androidx.room.*
import com.example.jobportal.common.local.entity.UserEntity
import com.example.jobportal.common.local.entity.UserType


@Dao
interface UserDao {

    @Query("SELECT * FROM userEntity")
    suspend fun getAll(): List<UserEntity>

    @Query("SELECT * FROM userEntity WHERE type IN (:userIds)")
    suspend fun getByType(userIds: UserType): List<UserEntity>

//    @Query("SELECT * FROM userEntity WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    suspend fun findByName(first: String, last: String): UserEntity

    @Query("SELECT * FROM userEntity WHERE email LIKE :email LIMIT 1")
    suspend fun findByEmail(email: String): UserEntity

    @Insert
    suspend fun insertAll(vararg users: UserEntity): LongArray?

    @Delete
    suspend fun delete(user: UserEntity)

}