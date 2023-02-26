package com.example.jobportal.di

import com.example.jobportal.common.local.MainDb
import com.example.jobportal.common.local.dao.JobOfferDao
import com.example.jobportal.common.local.dao.JobSeekerDao
import com.example.jobportal.common.local.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DbModule {

    @Provides
    fun provideImageDao(db: MainDb): UserDao {
        return db.userDao
    }

    @Provides
    fun provideJobSeekerDao(db: MainDb): JobSeekerDao {
        return db.jobSeekerDao
    }

    @Provides
    fun provideJobOfferDao(db: MainDb): JobOfferDao {
        return db.jobOfferDao
    }

}