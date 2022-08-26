package com.example.anifox.presentation.myList.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.anifox.adapters.common.SmallerAnimeItem
import com.example.anifox.databinding.FragmentOnCompletedTabBinding
import com.example.anifox.presentation.myList.MyListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class OnFavouriteOnHoldTab : Fragment() {
    private var _binding: FragmentOnCompletedTabBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        SmallerAnimeItem()
    }
    private val viewModel: MyListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnCompletedTabBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeOnState()
        initRecycler()
    }

    private fun observeOnState(){
        viewModel.getListOnHoldPaging().onEach {
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