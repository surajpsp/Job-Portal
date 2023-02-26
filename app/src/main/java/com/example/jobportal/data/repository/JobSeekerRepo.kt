package com.example.jobportal.data.repository

import com.example.jobportal.common.Resource
import com.example.jobportal.common.local.dao.JobSeekerDao
import com.example.jobportal.common.mapper.toJobSeekerData
import com.example.jobportal.common.mapper.toJobSeekerEntity
import com.example.jobportal.ui.job_dashboard.JobSeekerData
import com.example.jobportal.ui.repository.JobSeekerRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class JobSeekerRepo @Inject constructor(
    val dao: JobSeekerDao
) : JobSeekerRepository {
    override suspend fun getAllJobSeeker(): Flow<Resource<List<JobSeekerData>>> {
        return flow {
            emit(Resource.Loading())
            val response = dao.getAll()
            emit(Resource.Loading(false))
            emit(Resource.Success(response.map { it.toJobSeekerData() }))
        }
    }

    override suspend fun updateJobSeeker(jobSeekerData: JobSeekerData): Flow<Resource<List<JobSeekerData>>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertJobSeeker(jobSeekerData: JobSeekerData): Flow<Resource<List<JobSeekerData>>> {
        return flow {
            emit(Resource.Loading())
            dao.insertAll(jobSeekerData.toJobSeekerEntity())
            delay(2000)
            emit(Resource.Loading(false))
            val responseList = dao.getAll()
            emit(Resource.Success(responseList.map { it.toJobSeekerData() }))
        }
    }
}