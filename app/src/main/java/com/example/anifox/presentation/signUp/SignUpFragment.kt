package com.example.anifox.presentation.signUp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.anifox.R
import com.example.anifox.databinding.FragmentSignUpBinding
import com.example.anifox.util.validation.AuthValidate
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

    private fun initListeners() {

        binding.etEmail.addTextChangedListener(AuthValidate(requireContext()).TextFieldValidation(tie = binding.etEmail, til = binding.etEmailTIL, type = 1, pass = null))
        binding.etPassword.addTextChangedListener(AuthValidate(requireContext()).TextFieldValidation(tie = binding.etPassword, til = binding.etPasswordTIL, type = 1, pass = null))
        binding.etPasswordConfirm.addTextChangedListener(AuthValidate(requireContext()).TextFieldValidation(tie = binding.etPasswordConfirm, til = binding.etPasswordConfirmTIL, type = 1, pass = binding.etPassword))
        binding.etNickName.addTextChangedListener(AuthValidate(requireContext()).TextFieldValidation(tie = binding.etNickName, til = binding.etNickNameTIL, type = 1, pass = null))

        binding.btnRegister.setOnClickListener {
            if(isValidate()) {
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

    private fun isValidate(): Boolean {
        val password = AuthValidate(requireContext()).ValidateSignUp().password(tie = binding.etPassword, til = binding.etPasswordTIL, type = 2)
        val email = AuthValidate(requireContext()).ValidateSignUp().email(tie = binding.etEmail, til = binding.etEmailTIL, type = 2)
        val confirmPassword = AuthValidate(requireContext()).ValidateSignUp().passwordConfirm(tie = binding.etPasswordConfirm, til = binding.etPasswordConfirmTIL, type = 2, pass = binding.etPassword)
        val nickName = AuthValidate(requireContext()).ValidateSignUp().nickName(tie = binding.etNickName, til = binding.etNickNameTIL, type = 2)

        return password && email && confirmPassword && nickName
    }


    private fun <T> Flow<T>.launchWhenStarted() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted { collect () }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}