package com.example.jobportal.common.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jobportal.common.local.dao.JobOfferDao
import com.example.jobportal.common.local.dao.JobSeekerDao
import com.example.jobportal.common.local.dao.UserDao
import com.example.jobportal.common.local.entity.JobOfferEntity
import com.example.jobportal.common.local.entity.JobSeekerEntity
import com.example.jobportal.common.local.entity.UserEntity

@Database(
    entities = [
        UserEntity::class,
        JobOfferEntity::class,
        JobSeekerEntity::class],
    version = 1
)
abstract class MainDb : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val jobOfferDao: JobOfferDao
    abstract val jobSeekerDao: JobSeekerDao
}