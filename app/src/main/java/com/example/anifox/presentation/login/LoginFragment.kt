package com.example.anifox.presentation.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.anifox.BuildConfig.*
import com.example.anifox.R
import com.example.anifox.databinding.FragmentLoginFragmentBinding
import com.example.anifox.util.Constants.REDIRECT_URI
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        binding.codeBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("$APP_LINK?client_id=$CLIENT_ID&redirect_uri=$REDIRECT_URI&response_type=$RESPONSE_TYPE")
            startActivity(intent)
        }

        binding.signIn.setOnClickListener {
//            val authCode = binding.codeTie.text.toString()
//            if (authCode == "") {
//                binding.codeTie.error = getString(R.string.empty_view)
//            } else {
//                CoroutineScope(Dispatchers.Main).launch {
//                    viewModel.getTokens(authCode)
//                    viewModel.tokenState.collectLatest {
//                        if (it.token != null) {
//                            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
//                        }
//                    }
//                }
//            }
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}