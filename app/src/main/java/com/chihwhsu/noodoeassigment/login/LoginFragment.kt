package com.chihwhsu.noodoeassigment.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chihwhsu.noodoeassigment.data.UserInformationBody
import com.chihwhsu.noodoeassigment.databinding.FragmentLoginBinding
import com.chihwhsu.noodoeassigment.ext.getViewModelFactory

class LoginFragment : Fragment() {

    private val viewModel by viewModels<LoginViewModel>{ getViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentLoginBinding.inflate(inflater,container,false)

        binding.textInputEmail.doAfterTextChanged {
            viewModel.setUsername(it.toString())
        }

        binding.textInputPassword.doAfterTextChanged {
            viewModel.setPassword(it.toString())
        }

        binding.buttonLogin.setOnClickListener {
            viewModel.logIn()
        }






        return binding.root
    }
}