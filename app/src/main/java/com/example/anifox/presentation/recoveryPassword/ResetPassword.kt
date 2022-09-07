package com.example.anifox.presentation.recoveryPassword

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.anifox.R
import com.example.anifox.databinding.FragmentResetPasswordBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class ResetPassword : Fragment() {

    private var _binding: FragmentResetPasswordBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ResetPasswordViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResetPasswordBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
    }

    private fun initListeners(){
        binding.abSend.setOnClickListener {
            if (validateEmail()) {
                val email = binding.etEmail.text.toString()
                viewModel.sendInstructions(email = email)

                val bundle = Bundle()
                bundle.putString("email", email)
                findNavController().navigate(R.id.action_resetPassword_to_checkMail, bundle)
            }
        }

        binding.llBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

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