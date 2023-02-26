package com.example.jobportal.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.jobportal.databinding.JobSeekerRowBinding
import com.example.jobportal.ui.job_dashboard.AdapterListener
import com.example.jobportal.ui.job_dashboard.JobSeekerData

class JobSeekerAdapter(
    var list: List<JobSeekerData>,
    private val adapterListener: AdapterListener
) : RecyclerView.Adapter<JobSeekerAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            JobSeekerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = list.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding as JobSeekerRowBinding
        binding.apply {
            this.position = position
            this.data = list[position]
            this.listener = adapterListener
            executePendingBindings()
        }
    }
}