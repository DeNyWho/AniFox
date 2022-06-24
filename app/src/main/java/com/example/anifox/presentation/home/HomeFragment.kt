package com.example.anifox.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.anifox.adapters.HomePopularReviewAdapter
import com.example.anifox.databinding.FragmentHomeFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

@AndroidEntryPoint
open class HomeFragment : Fragment() {

    private var _binding: FragmentHomeFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeFragmentViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPopularReview()
        Timber.d("Popular data:  ${viewModel.animePopularReview.value.data}")

        observeOnState()
    }

    private fun observeOnState(){
        viewModel.animePopularReview.onEach { state ->
            when(state.isLoading) {
                true -> {}
                false -> {
                    if(state.data != null) {
                        binding.popularRecycler.apply {
                            adapter =
                                viewModel.animePopularReview.value.data?.let {
                                    HomePopularReviewAdapter(
                                        it
                                    )
                                }
                        }
                    }
                }
            }
        }.launchWhenStarted()
    }

    private fun <T> Flow<T>.launchWhenStarted() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted { collect () }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}