package com.example.jobportal.ui.repository

import com.example.jobportal.common.Resource
import com.example.jobportal.ui.job_dashboard.JobSeekerData
import kotlinx.coroutines.flow.Flow

interface JobSeekerRepository {

    suspend fun getAllJobSeeker(): Flow<Resource<List<JobSeekerData>>>

    suspend fun updateJobSeeker(
        jobSeekerData: JobSeekerData
    ): Flow<Resource<List<JobSeekerData>>>

    suspend fun insertJobSeeker(
        jobSeekerData: JobSeekerData
    ): Flow<Resource<List<JobSeekerData>>>

}