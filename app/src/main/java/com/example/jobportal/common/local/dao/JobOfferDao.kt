package com.example.jobportal.common.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.jobportal.common.local.entity.JobOfferEntity

@Dao
interface JobOfferDao {

    @Query("SELECT * FROM jobOfferEntity")
    suspend fun getAll(): List<JobOfferEntity>

//    @Query("SELECT * FROM jobOfferEntity WHERE type IN (:userIds)")
//    suspend fun getByType(userIds: UserType): List<JobOfferEntity>
//
//    @Query("SELECT * FROM jobOfferEntity WHERE email LIKE :email LIMIT 1")
//    suspend fun findByEmail(email: String): JobOfferEntity

    @Insert
    suspend fun insertAll(vararg users: JobOfferEntity): LongArray?

    @Delete
    suspend fun delete(user: JobOfferEntity)

    @Query("DELETE FROM jobOfferEntity")
    suspend fun deleteAll()

}