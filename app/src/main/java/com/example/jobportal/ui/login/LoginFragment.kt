package com.example.jobportal.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.jobportal.R
import com.example.jobportal.common.Resource
import com.example.jobportal.common.isEmailValid
import com.example.jobportal.common.isPasswordValid
import com.example.jobportal.common.onTextChange
import com.example.jobportal.common.shared_cache.MySharedPrefs
import com.example.jobportal.databinding.FragmentRegistrationBinding
import com.example.jobportal.ui.data.UserData
import com.example.jobportal.ui.registration.RegistrationModel
import com.github.razir.progressbutton.showDrawable
import com.github.razir.progressbutton.showProgress
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewModel: RegistrationModel by viewModels()
    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!
    private val TAG = "LoginFragment"

    private lateinit var mySharedPrefs: MySharedPrefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mySharedPrefs = MySharedPrefs(requireContext())

        if (mySharedPrefs.email.isNullOrEmpty().not()) {
            findNavController().navigate(R.id.action_loginFragment_to_FirstFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val usernameEditText = binding.username
        val passwordEditText = binding.password
        val loginButton = binding.login
        usernameEditText.onTextChange(binding.usernameBox)
        passwordEditText.onTextChange(binding.passwordBox)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loginState.collect {
                    Log.d(TAG, "onViewCreated: state -> $it")
                    when (it) {
                        is Resource.Error -> onError(it.msg)
                        is Resource.Loading -> onLoading(it.isLoading)
                        is Resource.Success -> onSuccess(it.data)
                    }
                }
            }
        }



        passwordEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                onLogin()
            }
            false
        }

        loginButton.setOnClickListener {
            onLogin()
        }

        binding.registerBtn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationActivity)
        }
    }

    private fun onSuccess(data: UserData?) = lifecycleScope.launch() {

        dismissProgress()
        mySharedPrefs.id = data?.id!!
        mySharedPrefs.name = data.name
        mySharedPrefs.email = data.email
        mySharedPrefs.mobile = data.mobile
        mySharedPrefs.userType = data.type.toString()
        mySharedPrefs.companyName = data.company.toString()

        delay(1000)
        findNavController().navigate(R.id.action_loginFragment_to_FirstFragment)
    }

    private fun onLoading(loading: Boolean) {
        if (loading) showProgress()
    }

    private fun onError(msg: String?) {
        binding.login.text = "Login"
        binding.login.isEnabled = true
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    private fun onLogin() {

        if (isEmailValid(binding.username.text.toString()).not()) {
            binding.usernameBox.error = "Email Invalid!"
            return
        }

        if (isPasswordValid(binding.password.text.toString()).not()) {
            binding.passwordBox.error = "Password Invalid!"
            return
        }

        binding.login.isEnabled = false
        viewModel.onLogin(binding.username.text.toString(), binding.password.text.toString())
    }

    private fun showProgress() = binding.login.showProgress {
        buttonTextRes = R.string.progress
        progressColor = ContextCompat.getColor(
            requireContext(), com.google.android.material.R.color.design_default_color_primary
        )
    }

    private fun dismissProgress() {
        val animatedDrawable =
            ContextCompat.getDrawable(requireContext(), R.drawable.round_done_all_24)
        //Defined bounds are required for your drawable
        animatedDrawable?.setBounds(0, 0, 40, 40)

        binding.login.showDrawable(animatedDrawable!!) {
            buttonTextRes = R.string.successLogin
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}