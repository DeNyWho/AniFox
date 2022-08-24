package com.example.anifox.presentation.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.anifox.R
import com.example.anifox.databinding.SplashFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private var _binding: SplashFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SplashFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getTokenFromPreferences()
        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.token.observe(viewLifecycleOwner){
                println("TOKENTS = $it")
                if(it == null){
                    findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
                } else {
                    findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
                }
            }

        }, 1500)
    }

    private fun <T> Flow<T>.launchWhenStarted() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted { collect () }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}