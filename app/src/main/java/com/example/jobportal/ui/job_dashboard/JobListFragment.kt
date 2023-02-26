package com.example.jobportal.ui.job_dashboard

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.jobportal.R
import com.example.jobportal.common.Resource
import com.example.jobportal.common.local.entity.UserType
import com.example.jobportal.common.shared_cache.MySharedPrefs
import com.example.jobportal.databinding.FragmentFirstBinding
import com.example.jobportal.ui.adapter.JobRecruiterAdapter
import com.example.jobportal.ui.adapter.JobSeekerAdapter
import com.example.jobportal.ui.post_job.PostJobFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class JobListFragment : Fragment(), AdapterListener {

    private var _binding: FragmentFirstBinding? = null
    private val TAG = "JobListFragment"
    private val binding get() = _binding!!
    private val viewModel by viewModels<JobListViewModel>()
    private lateinit var sharedPrefs: MySharedPrefs

    private val jobSeekerAdapter: JobSeekerAdapter by lazy { JobSeekerAdapter(listOf(), this) }
    private val jobOfferAdapter: JobRecruiterAdapter by lazy { JobRecruiterAdapter(listOf(), this) }

    private var stateJob: Job? = null

    private val menuProvider = object : MenuProvider {
        override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
            menuInflater.inflate(R.menu.menu_main, menu)
        }

        override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
            return when (menuItem.itemId) {
                R.id.action_logout -> {
                    onLogOut()
                    false
                }
                else -> {
                    findNavController().navigate(R.id.action_FirstFragment_to_profieFragment)
                    false
                }
            }
        }
    }

    private fun onLogOut() {
        Toast.makeText(requireContext(), "Logout Success!", Toast.LENGTH_SHORT).show()
        sharedPrefs.onClear()
        findNavController().navigate(R.id.action_FirstFragment_to_loginFragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPrefs = MySharedPrefs(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(menuProvider, viewLifecycleOwner, Lifecycle.State.RESUMED)

        if (sharedPrefs.userType.isNullOrEmpty()
                .not() && sharedPrefs.userType == UserType.RECRUITER.name
        ) {
            binding.jobList.adapter = jobSeekerAdapter
        } else {
            binding.jobList.adapter = jobOfferAdapter
            binding.fab.visibility = View.GONE
        }

        binding.fab.setOnClickListener {
            val fragment = PostJobFragment { jobOfferData ->
                viewModel.addJobOffer(jobOfferData)
                Toast.makeText(requireContext(), "Job Offer Added Success!", Toast.LENGTH_SHORT)
                    .show()
            }
            fragment.show(requireActivity().supportFragmentManager, fragment.tag)
        }

        stateJob = viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.jobOfferState.collectLatest {
                    Log.d(TAG, "onViewCreated: jobOfferState -> $it")
                    when (it) {
                        is Resource.Error -> onError(it.msg)
                        is Resource.Loading -> onLoading(it.isLoading)
                        is Resource.Success -> onJobOfferLoaded(it.data)
                    }
                }
            }
        }

        stateJob = viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.jobSeekerState.collectLatest {
                    Log.d(TAG, "onViewCreated: jobOfferState -> $it")
                    when (it) {
                        is Resource.Error -> onError(it.msg)
                        is Resource.Loading -> onLoading(it.isLoading)
                        is Resource.Success -> onJobSeekerLoaded(it.data)
                    }
                }
            }
        }

        if (sharedPrefs.userType.isNullOrEmpty()
                .not() && sharedPrefs.userType == UserType.RECRUITER.name
        ) {
            viewModel.getJobSeekerData()
        } else {
            viewModel.getJobOfferData()
        }
    }


    override fun onStop() {
        stateJob?.cancel()
        super.onStop()
    }


    private fun onJobSeekerLoaded(data: List<JobSeekerData>?) {
        data?.let {
            jobSeekerAdapter.apply {
                list = it
                notifyItemRangeInserted(0, data.lastIndex)
                notifyDataSetChanged()
            }
        }
    }

    private fun onJobOfferLoaded(data: List<JobOfferData>?) {
        Log.d(TAG, "onJobOfferLoaded: list -> $data")
        data?.let {
            jobOfferAdapter.apply {
                list = it
                notifyItemRangeInserted(0, data.lastIndex)
                notifyDataSetChanged()
            }
        }

    }

    private fun onLoading(loading: Boolean) {

    }

    private fun onError(msg: String?) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClickCard(position: Int, data: Any) {
        if (sharedPrefs.userType.isNullOrEmpty()
                .not() && sharedPrefs.userType == UserType.RECRUITER.name
        ) {

            val jobSeekerData = data as JobSeekerData

            JobListFragmentDirections.actionFirstFragmentToJobDashboardDetail(jobSeekerData)
                .setData(jobSeekerData)
                .apply {
                    findNavController().navigate(this)
                }


        } else {

            findNavController().navigate(R.id.action_FirstFragment_to_applyJobFragment)

        }
    }
}