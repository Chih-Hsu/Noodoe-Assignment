package com.chihwhsu.noodoeassigment.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.chihwhsu.noodoeassigment.databinding.FragmentLoginBinding
import com.chihwhsu.noodoeassigment.ext.getViewModelFactory

class LoginFragment : Fragment() {

    private val viewModel by viewModels<LoginViewModel> { getViewModelFactory() }
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.textInputEmail.doAfterTextChanged {
            viewModel.setUsername(it.toString())
        }

        binding.textInputPassword.doAfterTextChanged {
            viewModel.setPassword(it.toString())
        }

        binding.buttonLogin.setOnClickListener {
            login()
        }

        viewModel.navigation.observe(viewLifecycleOwner) {
            if (it == true) {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToParkingLotsFragment())
                viewModel.doneNavigation()
            }
        }

        viewModel.error.observe(viewLifecycleOwner) {
            showError()
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            showProgressBar(isLoading)
        }

        return binding.root
    }

    private fun showProgressBar(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showError() {
        binding.textErrorMessage.visibility = View.VISIBLE
        binding.textErrorMessage.text = "帳號密碼錯誤"
    }

    private fun login() {

        if (viewModel.isEmailFormatCorrect() && viewModel.isPasswordFormatCorrect()) {
            binding.textErrorMessage.visibility = View.GONE
            viewModel.logIn()
        } else {
            setErrorTextMessage()
            binding.textErrorMessage.visibility = View.VISIBLE
        }
    }

    private fun setErrorTextMessage() {

        val errorMessage = if (binding.textInputEmail.text.isNullOrBlank()) {
            "請輸入E-Mail"
        } else if (!viewModel.isEmailFormatCorrect()) {
            "E-Mail 格式錯誤"
        } else if (!viewModel.isPasswordFormatCorrect()) {
            "請輸入密碼"
        } else {
            "帳號密碼錯誤"
        }
        binding.textErrorMessage.text = errorMessage
    }
}