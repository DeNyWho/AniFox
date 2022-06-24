package com.example.anifox.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.anifox.adapters.AnimeItem
import com.example.anifox.adapters.DiscoverItem
import com.example.anifox.databinding.FragmentHomeFragmentBinding
import com.xwray.groupie.GroupieAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

@AndroidEntryPoint
open class HomeFragment : Fragment() {

    private var _binding: FragmentHomeFragmentBinding? = null
    private val binding get() = _binding!!

    private var popularAdapter: GroupieAdapter? = null
    private var discoverAdapter: GroupieAdapter? = null


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
        viewModel.getDiscover()
        Timber.d("Popular data:  ${viewModel.animePopularReview.value.data}")
        observePopularOnState()
        observeDiscoverOnState()
    }

    private fun observePopularOnState(){
        popularAdapter = GroupieAdapter()
        binding.popularRecycler.adapter = popularAdapter
        viewModel.animePopularReview.onEach { state ->
            when(state.isLoading) {
                true -> {}
                false -> {
                    if(state.data != null) {
                        viewModel.animePopularReview.value.data?.onEach {
                            popularAdapter?.add(AnimeItem(it))
                        }
                    }
                }
            }
        }.launchWhenStarted()
    }

    private fun observeDiscoverOnState(){
        discoverAdapter = GroupieAdapter()
        binding.discoverRecycler.adapter = discoverAdapter
        viewModel.animeDiscover.onEach { state ->
            when(state.isLoading) {
                true -> {}
                false -> {
                    if(state.data != null) {
                        viewModel.animeDiscover.value.data?.onEach {
                            discoverAdapter?.add(DiscoverItem(it))
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