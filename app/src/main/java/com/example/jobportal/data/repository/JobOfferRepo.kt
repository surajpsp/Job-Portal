package com.example.jobportal.data.repository

import com.example.jobportal.common.Resource
import com.example.jobportal.common.local.dao.JobOfferDao
import com.example.jobportal.common.mapper.toJobOfferData
import com.example.jobportal.common.mapper.toJobOfferEntity
import com.example.jobportal.ui.job_dashboard.JobOfferData
import com.example.jobportal.ui.repository.JobOfferRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class JobOfferRepo @Inject constructor(
    private val dao: JobOfferDao
) : JobOfferRepository {

    override suspend fun getAllJobOffer(): Flow<Resource<List<JobOfferData>>> {
        return flow {
            emit(Resource.Loading())
            val response = dao.getAll()
            emit(Resource.Loading(false))
            emit(Resource.Success(response.map { it.toJobOfferData() }))
        }
    }

    override suspend fun updateJobOffer(jobOfferData: JobOfferData): Flow<Resource<List<JobOfferData>>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertJobOffer(jobOfferData: JobOfferData): Flow<Resource<List<JobOfferData>>> {
        return flow {
            emit(Resource.Loading())
            val response = dao.insertAll(jobOfferData.toJobOfferEntity())
            emit(Resource.Loading(false))
            val responseList = dao.getAll()
            emit(Resource.Success(responseList.map { it.toJobOfferData() }))
        }
    }
}