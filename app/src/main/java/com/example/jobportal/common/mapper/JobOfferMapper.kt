package com.example.jobportal.common.mapper

import com.example.jobportal.common.local.entity.JobOfferEntity
import com.example.jobportal.ui.job_dashboard.JobOfferData

fun JobOfferEntity.toJobOfferData(): JobOfferData {
    return JobOfferData(
        id, title, budget, experiance, company, jobType, user, status, date, appliedDate
    )
}


fun JobOfferData.toJobOfferEntity(): JobOfferEntity {
    return JobOfferEntity(
        title = title,
        budget = budget,
        experiance = experiance,
        company = company,
        jobType = jobType,
        user = user,
        status = status,
        date = date,
        appliedDate = appliedDate
    )
}