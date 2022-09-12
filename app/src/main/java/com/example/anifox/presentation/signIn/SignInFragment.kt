package com.example.anifox.presentation.signIn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.anifox.R
import com.example.anifox.databinding.FragmentSignInBinding
import com.example.anifox.util.validation.AuthValidate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SignInViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeOnState()
        initListeners()
    }

    private fun initListeners(){

        binding.etEmail.addTextChangedListener(AuthValidate(requireContext()).TextFieldValidation(
            type = 1,
            tie = binding.etEmail,
            til = binding.etEmailTIL,
            pass = null,
            onListen = null))
        binding.etPassword.addTextChangedListener(AuthValidate(requireContext()).TextFieldValidation(
            type = 1,
            tie = binding.etPassword,
            til = binding.etPasswordTIL,
            pass = null,
            onListen = null))

        binding.signIn.setOnClickListener {
            val mail = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (isValidate()) {
                viewModel.signIn(email = mail, password = password)
            }

        }

        binding.tvForgot.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_resetPassword)
        }

        binding.llSkip.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }

        binding.tvRegistration.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
    }

    fun observeOnState() {
        viewModel.state.onEach { state ->
            if (state.data?.isNotEmpty() == true) {
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }
            if (state.message == "Wrong login or password") {
                AuthValidate(requireContext()).ValidateSignIn().passwordWrong(
                    passwordTie = binding.etPassword,
                    emailTie = binding.etEmail,
                    passwordTil = binding.etPasswordTIL,
                    emailTil = binding.etEmailTIL
                )
            }
        }.launchWhenStarted()
    }

    private fun isValidate(): Boolean {
        val password = AuthValidate(requireContext()).ValidateSignIn().password(tie = binding.etPassword, til = binding.etPasswordTIL, type = 2)
        val email = AuthValidate(requireContext()).ValidateSignIn().email(tie = binding.etEmail, til = binding.etEmailTIL, type = 2)

        return password && email
    }


    private fun <T> Flow<T>.launchWhenStarted() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted { collect () }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}