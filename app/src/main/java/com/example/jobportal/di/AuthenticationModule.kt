package com.example.jobportal.di

import com.example.jobportal.data.repository.JobOfferRepo
import com.example.jobportal.data.repository.JobSeekerRepo
import com.example.jobportal.data.repository.UserRepo
import com.example.jobportal.ui.repository.AuthenticationRepository
import com.example.jobportal.ui.repository.JobOfferRepository
import com.example.jobportal.ui.repository.JobSeekerRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindUserRepository(
        userRepo: UserRepo
    ): AuthenticationRepository

    @Binds
    abstract fun bindJobSeeker(
        jobSeekerRepo: JobSeekerRepo
    ): JobSeekerRepository

    @Binds
    abstract fun bindJobOffer(
        jobOfferRepo: JobOfferRepo
    ): JobOfferRepository
}