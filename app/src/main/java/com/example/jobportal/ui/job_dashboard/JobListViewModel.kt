package com.example.jobportal.ui.job_dashboard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobportal.common.Resource
import com.example.jobportal.ui.repository.JobOfferRepository
import com.example.jobportal.ui.repository.JobSeekerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JobListViewModel @Inject constructor(
    private val jobSeekerRepository: JobSeekerRepository,
    private val jobOfferRepository: JobOfferRepository
) : ViewModel() {

    //    private val _data = savedStateHandle.get<UserData>("data")
//    val data get() = _data
    private val TAG = "JobListViewModel"

    var listData:List<Any>? = null
    private val jobOfferData:JobOfferData? = null

    private val _jobSeekerState =
        MutableStateFlow<Resource<List<JobSeekerData>>>(Resource.Loading(false))
    val jobSeekerState = _jobSeekerState.asStateFlow()

    private val _jobOfferState =
        MutableStateFlow<Resource<List<JobOfferData>>>(Resource.Loading(false))
    val jobOfferState = _jobOfferState.asStateFlow()


    fun addJobOffer(jobOfferData: JobOfferData) = viewModelScope.launch {
        val response = jobOfferRepository.insertJobOffer(jobOfferData)
        response.collect {
            Log.d(TAG, "onLogin: collect - ${it.data}")
            _jobOfferState.value = it
        }
    }

    fun getJobSeekerData() = viewModelScope.launch {
        val response = jobSeekerRepository.getAllJobSeeker()
        response.collect {
            Log.d(TAG, "onLogin: collect - ${it.data}")
            listData = it.data
            _jobSeekerState.value = it
        }
    }

    fun getJobOfferData() = viewModelScope.launch {
        val response = jobOfferRepository.getAllJobOffer()
        response.collect {
            Log.d(TAG, "onLogin: collect - ${it.data}")
            listData = it.data
            _jobOfferState.value = it
        }
    }


}