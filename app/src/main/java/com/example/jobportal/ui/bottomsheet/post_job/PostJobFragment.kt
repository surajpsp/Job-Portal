package com.example.jobportal.ui.bottomsheet.post_job

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.jobportal.R
import com.example.jobportal.common.onTextChange
import com.example.jobportal.common.shared_cache.MySharedPrefs
import com.example.jobportal.databinding.FragmentPostJobBinding
import com.example.jobportal.ui.job_dashboard.JobOfferData
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class PostJobFragment(private val onAdd: (JobOfferData) -> Unit) : BottomSheetDialogFragment() {


    private var _binding: FragmentPostJobBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentPostJobBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.jobTitle.onTextChange(binding.jobTitleBox)
        binding.sallery.onTextChange(binding.salleryBox)
        binding.jobType.onTextChange(binding.jobTitleBox)
        binding.experiance.onTextChange(binding.experianceBox)
        binding.jobDescription.onTextChange(binding.jobDescriptionBox)
        binding.lastDate.onTextChange(binding.lastDateBox)

        val array = resources.getStringArray(R.array.jobType)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.drop_down_layout, array)
        binding.jobType.setAdapter(arrayAdapter)

        val arrayExperiance = resources.getStringArray(R.array.experiance)
        val arrayAdapterExperiance =
            ArrayAdapter(requireContext(), R.layout.drop_down_layout, arrayExperiance)
        binding.experiance.setAdapter(arrayAdapterExperiance)

        binding.login.setOnClickListener {
            onPostJob()
        }
    }

    private fun onPostJob() {

        if (binding.jobTitle.text.isNullOrEmpty()) {
            binding.jobTitleBox.error = "Title Invalid!"
            return
        }

        if (binding.jobDescription.text.isNullOrEmpty()) {
            binding.jobDescriptionBox.error = "Description Invalid!"
            return
        }

        if (binding.lastDate.text.isNullOrEmpty()) {
            binding.lastDateBox.error = "Last Date Invalid!"
            return
        }

        if (binding.sallery.text.isNullOrEmpty()) {
            binding.salleryBox.error = "Sallery Invalid!"
            return
        }

        if (binding.jobType.text.isNullOrEmpty()) {
            binding.salleryBox.error = "Select Job Type!"
            return
        }

        if (binding.experiance.text.isNullOrEmpty()) {
            binding.experianceBox.error = "Select Experiance Type!"
            return
        }

        val sharedPrefs = MySharedPrefs(requireContext())

        val jobOffer = JobOfferData(
            id = id,
            title = binding.jobTitle.text.toString(),
            budget = binding.sallery.text.toString(),
            experiance = binding.experiance.text.toString(),
            company = sharedPrefs.companyName ?: "Confidential",
            jobType = binding.jobType.text.toString(),
            user = sharedPrefs.name!!,
            status = false,
            date = binding.lastDate.text.toString(),
            appliedDate = binding.jobDescription.text.toString()
        )

        this.dismissNow()
        this.dismiss()
        onAdd(jobOffer)
    }

}