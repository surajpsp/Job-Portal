package com.example.jobportal.application

import android.app.Application
import com.example.jobportal.common.local.dao.JobOfferDao
import com.example.jobportal.common.local.dao.JobSeekerDao
import com.example.jobportal.data.addFakeJobOffer
import com.example.jobportal.data.addFakeJobSeeker
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class MyApplication : Application() {

    @Inject
    lateinit var jobSeekerDao: JobSeekerDao

    @Inject
    lateinit var jobOfferDao: JobOfferDao

    override fun onCreate() {
        super.onCreate()

        GlobalScope.launch {
            jobSeekerDao.deleteAll()
            jobSeekerDao.insertAll(*addFakeJobSeeker().toTypedArray())

            jobOfferDao.deleteAll()
            jobOfferDao.insertAll(*addFakeJobOffer().toTypedArray())
        }
    }
}