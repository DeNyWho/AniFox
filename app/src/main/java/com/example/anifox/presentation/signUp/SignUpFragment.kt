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
import com.example.anifox.common.listeners.ItemListenerUserName
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
        observeOnState()
        initListeners()
    }

    private fun postUserNameFromValidation(name: String){
        viewModel.checkUserNameIsAvailable(name)

    }

    private fun getUserNameFromValidation(): Boolean {
        var result = false
        viewModel.state.onEach { state ->
            if(state.nameExistsState.message == "This username already exists!") {
                AuthValidate(requireContext()).ValidateSignUp().existName(tie = binding.etNickName, til = binding.etNickNameTIL, result = false)
                result = false
            }
            if(state.nameExistsState.message == "Everything is fine") {
                result = true
            }
        }.launchWhenStarted()
        return result
    }


    private fun initListeners() {

        binding.etEmail.addTextChangedListener(
            AuthValidate(requireContext()).TextFieldValidation(
                type = 1,
                tie = binding.etEmail,
                til = binding.etEmailTIL,
                pass = null,
                onListen = null
            )
        )
        binding.etPassword.addTextChangedListener(
            AuthValidate(requireContext()).TextFieldValidation(
                type = 1,
                tie = binding.etPassword,
                til = binding.etPasswordTIL,
                pass = null,
                onListen = null
            )
        )
        binding.etPasswordConfirm.addTextChangedListener(
            AuthValidate(requireContext()).TextFieldValidation(
                type = 1,
                tie = binding.etPasswordConfirm,
                til = binding.etPasswordConfirmTIL,
                pass = binding.etPassword,
                onListen = null
            )
        )
        binding.etNickName.addTextChangedListener(
            AuthValidate(requireContext()).TextFieldValidation(
                tie = binding.etNickName,
                til = binding.etNickNameTIL,
                type = 1,
                pass = null,
                onListen = object : ItemListenerUserName {
                    override fun postUserName(name: String) {
                        postUserNameFromValidation(name)
                    }
                    override fun getUserName(): Boolean {
                        return getUserNameFromValidation()
                    }
                }
            )
        )

        binding.btnRegister.setOnClickListener {
            if (isValidate()) {
                val username = binding.etNickName.text.toString()
                val email = binding.etEmail.text.toString()
                val password = binding.etPassword.text.toString()

                viewModel.signUp(username = username, email = email, password = password)
            }
        }

        binding.llSkip.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_homeFragment)
        }

        binding.tvLogin.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }
    }

    fun observeOnState(){
        viewModel.state.onEach { state ->
            if (state.userSignUpState.data?.isNotEmpty() == true) {
                findNavController().navigate(R.id.action_signUpFragment_to_homeFragment)
            }
            if(state.userSignUpState.message == "Username and email is already taken!") {
                val resultEmail = AuthValidate(requireContext()).ValidateSignUp().existEmail(tie = binding.etEmail, til = binding.etEmailTIL)
                val resultName = AuthValidate(requireContext()).ValidateSignUp().existName(tie = binding.etNickName, til = binding.etNickNameTIL, result = false)
                if (resultEmail && resultName) viewModel.authNotSuccess()
            } else if(state.userSignUpState.message == "This email already exists!") {
                val result = AuthValidate(requireContext()).ValidateSignUp().existEmail(tie = binding.etEmail, til = binding.etEmailTIL)
                if (result) viewModel.authNotSuccess()
            } else if(state.userSignUpState.message == "This username already exists!") {
                val result = AuthValidate(requireContext()).ValidateSignUp().existName(tie = binding.etNickName, til = binding.etNickNameTIL, result = false)
                if (result) viewModel.authNotSuccess()
            }

            if(state.nameExistsState.message == "This username already exists!") {
                AuthValidate(requireContext()).ValidateSignUp().existName(tie = binding.etNickName, til = binding.etNickNameTIL, result = false)
            }
            if(state.nameExistsState.message == "Everything is fine") {
                AuthValidate(requireContext()).ValidateSignUp().existName(tie = binding.etNickName, til = binding.etNickNameTIL, result = true)
            }
        }.launchWhenStarted()
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