package com.chihwhsu.noodoeassigment.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.chihwhsu.noodoeassigment.R
import com.chihwhsu.noodoeassigment.databinding.FragmentLoginBinding
import com.chihwhsu.noodoeassigment.ext.getViewModelFactory

class LoginFragment : Fragment() {

    private val viewModel by viewModels<LoginViewModel> { getViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.textInputEmail.doAfterTextChanged {
            viewModel.setUsername(it.toString())
        }

        binding.textInputPassword.doAfterTextChanged {
            viewModel.setPassword(it.toString())
        }

        binding.buttonLogin.setOnClickListener {
            viewModel.logIn()
        }

        viewModel.navigation.observe(viewLifecycleOwner) {
            if (it == true) {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToParkingLotsFragment())
            }
        }

        viewModel.error.observe(viewLifecycleOwner) {

        }

        return binding.root
    }
}