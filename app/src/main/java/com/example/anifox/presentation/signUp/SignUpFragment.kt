package com.example.anifox.presentation.signUp

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.anifox.R
import com.example.anifox.databinding.FragmentSignUpBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
    }

    private fun initListeners(){

        binding.btnRegister.setOnClickListener {
            if (isValidate()) {
                val username = binding.etNickName.text.toString()
                val email = binding.etEmail.text.toString()
                val password = binding.etPassword.text.toString()

                viewModel.signUp(username = username, email = email, password = password)
                viewModel.state.onEach { state ->
                    if (state.data?.isNotEmpty() == true) {
                        findNavController().navigate(R.id.action_signUpFragment_to_homeFragment)
                    }
                }.launchWhenStarted()
            }
        }

        binding.llSkip.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_homeFragment)
        }

        binding.tvLogin.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }
    }

    private fun isValidate(): Boolean =
        validateUserName() && validateEmail() && validatePassword() && validateConfirmPassword()

    private fun validateEmail(): Boolean {
        if (binding.etEmail.text.toString().trim().isEmpty()) {
            binding.etEmailTIL.error = getString(R.string.require_field)
            binding.etEmail.requestFocus()
            return false
        } else if (!isValidEmail(binding.etEmail.text.toString())) {
            binding.etEmailTIL.error = getString(R.string.email_wrong)
            binding.etEmail.requestFocus()
            return false
        } else {
            binding.etEmailTIL.isErrorEnabled = false
        }
        return true
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun validateUserName(): Boolean {
        if (binding.etNickName.text.toString().trim().isEmpty()) {
            binding.etNickNameTIL.error = getString(R.string.require_field)
            binding.etNickName.requestFocus()
            return false
        } else {
            binding.etNickNameTIL.isErrorEnabled = false
        }
        return true
    }

    private fun validatePassword(): Boolean {
        if (binding.etPassword.text.toString().trim().isEmpty()) {
            binding.etPasswordTIL.error = getString(R.string.require_field)
            binding.etPassword.requestFocus()
            return false
        } else if (binding.etPassword.text.toString().length < 8) {
            binding.etPasswordTIL.error = getString(R.string.password_def_helper)
            binding.etPassword.requestFocus()
            return false
        } else {
            binding.etPasswordTIL.isErrorEnabled = false
        }
        return true
    }

    private fun validateConfirmPassword(): Boolean {
        when {
            binding.etPasswordConfirm.text.toString().trim().isEmpty() -> {
                binding.etPasswordConfirmTIL.error = getString(R.string.require_field)
                binding.etPasswordConfirm.requestFocus()
                return false
            }
            binding.etPasswordConfirm.text.toString() != binding.etPasswordConfirm.text.toString() -> {
                binding.etPasswordConfirmTIL.error = getString(R.string.password_dont_match)
                binding.etPasswordConfirm.requestFocus()
                return false
            }
            else -> {
                binding.etPasswordConfirmTIL.isErrorEnabled = false
            }
        }
        return true
    }

    private fun <T> Flow<T>.launchWhenStarted() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted { collect () }
    }

    private fun String.isEmailValid(): Boolean {
        return this.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}