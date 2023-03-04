package com.example.jobportal.common.local

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DbModule {

    @Provides
    @Singleton
    fun provideILocalDatabase(application: Application): MainDb {
        return Room.databaseBuilder(application, MainDb::class.java, "jobprinceprotalnewone.db").build()
    }

}