package com.example.anifox.presentation.recoveryPassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.anifox.R
import com.example.anifox.databinding.FragmentChangePasswordBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class ChangePassword : Fragment() {

    private var _binding: FragmentChangePasswordBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ResetPasswordViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChangePasswordBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
    }

    private fun initListeners(){
        binding.abChangePassword.setOnClickListener {
            if(isValidate()) {
                Toast.makeText(requireContext(), "validated", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun isValidate(): Boolean =
        validatePassword() && validateConfirmPassword()

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}