package com.example.jobportal.ui.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.jobportal.R
import com.example.jobportal.common.*
import com.example.jobportal.databinding.FragmentRegistrationActivityBinding
import com.github.razir.progressbutton.showDrawable
import com.github.razir.progressbutton.showProgress
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegistrationActivity : Fragment() {

    private var _binding: FragmentRegistrationActivityBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<RegistrationModel>()
    private val TAG = "RegistrationActivity"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRegistrationActivityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.name.onTextChange(binding.nameBox)
        binding.username.onTextChange(binding.usernameBox)
        binding.mobile.onTextChange(binding.mobileBox)
        binding.password.onTextChange(binding.passwordBox)
        binding.companyName.onTextChange(binding.companyNameBox)

        binding.recruiterCheck.setOnCheckedChangeListener { compoundButton, b ->
            binding.companyNameBox.visibility = if (b) View.VISIBLE else View.GONE
        }

        binding.registerBtn.setOnClickListener {
            onRegister()
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    when (it) {
                        is Resource.Error -> onError(it.msg)
                        is Resource.Loading -> onLoading(it.isLoading)
                        is Resource.Success -> onSuccess(it.data)
                    }
                }
            }
        }

    }

    private fun onSuccess(data: Boolean?) = lifecycleScope.launch() {
        dismissProgress()
        Toast.makeText(requireContext(), "Registration is Success!", Toast.LENGTH_SHORT).show()
        delay(1000)
        findNavController().popBackStack()
    }

    private fun onLoading(loading: Boolean) {
        if (loading) showProgress()
    }

    private fun showProgress() = binding.registerBtn.showProgress {
        buttonTextRes = R.string.progress
        progressColor = ContextCompat.getColor(
            requireContext(),
            com.google.android.material.R.color.design_default_color_primary
        )
    }

    private fun dismissProgress() {
        val animatedDrawable =
            ContextCompat.getDrawable(requireContext(), R.drawable.round_done_all_24)
        //Defined bounds are required for your drawable
        animatedDrawable?.setBounds(0, 0, 40, 40)

        binding.registerBtn.showDrawable(animatedDrawable!!) {
            buttonTextRes = R.string.successRegistration
        }
    }

    private fun onError(msg: String?) {
        binding.registerBtn.isEnabled = true
        binding.registerBtn.text = "Register"
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    private fun onRegister() {

        if (isNameValid(binding.name.text.toString())) {
            binding.nameBox.error = "Name Invalid!"
            return
        }

        if (isEmailValid(binding.username.text.toString()).not()) {
            binding.usernameBox.error = "Email Invalid!"
            return
        }

        if (binding.mobile.text?.length!! < 10) {
            binding.nameBox.error = "Name Invalid!"
            return
        }

        if (isPasswordValid(binding.password.text.toString()).not()) {
            binding.passwordBox.error = "Password Invalid!"
            return
        }


        if (isCompanyNameValid(binding.companyName.text.toString())) {
            binding.companyNameBox.error = "Company Name Invalid!"
            return
        }

        binding.registerBtn.isEnabled = false

        viewModel.registerNow(
            binding.name.text.toString(),
            binding.username.text.toString(),
            binding.mobile.text.toString(),
            binding.password.text.toString(),
            binding.companyName.text.toString(),
            binding.recruiterCheck.isChecked,
        )
    }


}