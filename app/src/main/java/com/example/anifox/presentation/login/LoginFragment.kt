package com.example.anifox.presentation.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.anifox.R
import com.example.anifox.databinding.FragmentLoginFragmentBinding

class LoginFragment : Fragment() {

    private var binding: FragmentLoginFragmentBinding? = null

    private lateinit var viewModel: LoginFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginFragmentBinding.inflate(inflater, container, false)
        binding!!.signIn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)

        }
        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}