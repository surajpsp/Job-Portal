package com.example.jobportal.ui.repository

import com.example.jobportal.common.Resource
import com.example.jobportal.ui.job_dashboard.JobOfferData
import kotlinx.coroutines.flow.Flow

interface JobOfferRepository {

    suspend fun getAllJobOffer(): Flow<Resource<List<JobOfferData>>>

    suspend fun updateJobOffer(
        jobOfferData: JobOfferData
    ): Flow<Resource<List<JobOfferData>>>

    suspend fun insertJobOffer(
        jobOfferData: JobOfferData
    ): Flow<Resource<List<JobOfferData>>>


}