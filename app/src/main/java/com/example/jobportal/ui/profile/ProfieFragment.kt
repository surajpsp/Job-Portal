package com.example.jobportal.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.jobportal.common.local.entity.UserType
import com.example.jobportal.common.shared_cache.MySharedPrefs
import com.example.jobportal.databinding.FragmentProfieBinding


class ProfieFragment : Fragment() {

    private var _binding: FragmentProfieBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentProfieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPrefs = MySharedPrefs(requireContext())

        binding.name.text = sharedPrefs.name!!
        binding.contactNumber.text = sharedPrefs.mobile!!
        binding.email.text = sharedPrefs.email!!
        binding.experiance.text = sharedPrefs.userType!!

        if (sharedPrefs.userType != UserType.RECRUITER.name) binding.skillsOrCompany.text =
            "Skills" else binding.skillsOrCompany.text = "Company Name"

        binding.occupation.text = sharedPrefs.companyName.toString()
    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}