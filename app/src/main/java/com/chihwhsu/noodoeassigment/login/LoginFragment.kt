package com.chihwhsu.noodoeassigment.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.chihwhsu.noodoeassigment.data.UserInformationBody
import com.chihwhsu.noodoeassigment.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLoginBinding.inflate(inflater,container,false)

        val viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.buttonLogin.setOnClickListener {
            val userInfo = UserInformationBody(binding.textInputEmail.text.toString(),binding.textInputPassword.text.toString())
            viewModel.logIn(userInfo)
        }






        return binding.root
    }
}