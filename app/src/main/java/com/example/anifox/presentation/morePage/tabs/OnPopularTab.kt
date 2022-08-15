package com.example.anifox.presentation.morePage.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.anifox.adapters.SmallerAnimeItem
import com.example.anifox.databinding.FragmentOnPopularTabBinding
import com.example.anifox.presentation.morePage.MorePageViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class OnPopularTab(private val genre: String?) : Fragment() {

    private var _binding: FragmentOnPopularTabBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        SmallerAnimeItem()
    }

    private val viewModel: MorePageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnPopularTabBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setQueriesOnPopular(genre = genre)

        observeOnState()
        initRecycler()
    }

    private fun observeOnState(){
        viewModel.getMangasOnPopularPaging().onEach {
            adapter.submitData(it)
        }.launchWhenStarted()
    }

    private fun <T> Flow<T>.launchWhenStarted() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted { collect () }
    }

    private fun initRecycler(){
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(context, 3)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}