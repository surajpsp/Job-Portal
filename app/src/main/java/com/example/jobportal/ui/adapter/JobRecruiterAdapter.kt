package com.example.jobportal.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.jobportal.databinding.JobListRowBinding
import com.example.jobportal.ui.job_dashboard.AdapterListener
import com.example.jobportal.ui.job_dashboard.JobOfferData

class JobRecruiterAdapter(
    var list: List<JobOfferData>,
    private val adapterListener: AdapterListener
) : RecyclerView.Adapter<JobRecruiterAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            JobListRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = list.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding as JobListRowBinding
        binding.apply {
            this.position = position
            this.data = list[position]
            this.listener = adapterListener
            executePendingBindings()
        }
    }
}