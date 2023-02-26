package com.example.jobportal.ui.job_dashboard_detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.jobportal.databinding.FragmentJobDashboardDetailBinding


class JobDashboardDetail : Fragment() {

    private var _binding: FragmentJobDashboardDetailBinding? = null
    private val binding get() = _binding!!

    private val navArgs by navArgs<JobDashboardDetailArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentJobDashboardDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = navArgs.data!!

        binding.name.text = data.name
        binding.contactNumber.text = data.mobile
        binding.occupation.text = data.title
        binding.email.text = data.email
        binding.experiance.text = data.experiance

        binding.button2.setOnClickListener {
            Toast.makeText(requireContext(), "Job Offer Approved !", Toast.LENGTH_SHORT).show()
        }

        binding.button.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + data.mobile))
            startActivity(intent)
        }
    }
}