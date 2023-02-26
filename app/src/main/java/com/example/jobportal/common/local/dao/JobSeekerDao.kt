package com.example.jobportal.common.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.jobportal.common.local.entity.JobSeekerEntity
import com.example.jobportal.common.local.entity.UserType


@Dao
interface JobSeekerDao {

    @Query("SELECT * FROM jobSeekerEntity")
    suspend fun getAll(): List<JobSeekerEntity>

//    @Query("SELECT * FROM jobSeekerEntity WHERE type IN (:userIds)")
//    suspend fun getByType(userIds: UserType): List<JobSeekerEntity>
//
//    @Query("SELECT * FROM jobSeekerEntity WHERE email LIKE :email LIMIT 1")
//    suspend fun findByEmail(email: String): JobSeekerEntity

    @Insert
    suspend fun insertAll(vararg users: JobSeekerEntity): LongArray?

    @Delete
    suspend fun delete(user: JobSeekerEntity)

    @Query("DELETE FROM jobSeekerEntity")
    suspend fun deleteAll()

}