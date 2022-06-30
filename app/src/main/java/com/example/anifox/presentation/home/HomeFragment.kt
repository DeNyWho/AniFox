package com.example.anifox.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anifox.R
import com.example.anifox.adapters.HeaderItem
import com.example.anifox.adapters.HeaderMoreItem
import com.example.anifox.adapters.HorizontalItem
import com.example.anifox.databinding.FragmentHomeFragmentBinding
import com.example.anifox.util.Constants
import com.example.anifox.util.Constants.ORDER_BY_POPULAR
import com.example.anifox.util.Constants.STATUS_BY_ANONS
import com.example.anifox.util.Constants.STATUS_BY_ONGOING
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
open class HomeFragment : Fragment() {

    private var _binding: FragmentHomeFragmentBinding? = null
    private val binding get() = _binding!!

    private val groupAdapter = GroupAdapter<GroupieViewHolder>()

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

        viewModel.getDiscover()
        viewModel.getPopularAiring()
        viewModel.getPopular()
        viewModel.getAnnounces()

        observeOnState()
        initRecycler()

    }

    private fun initRecycler(){
        binding.HomeRecycler.adapter = groupAdapter
        binding.HomeRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    private fun observeOnState() {
        viewModel.state.onEach { state ->
            val list = mutableListOf<Item<*>>().apply {
                this += HeaderItem(R.string.discover)
                this += HorizontalItem(state.discoverState.data ?: emptyList(), type = Constants.STYLE_BIGGER_RECYCLER)
                if(state.airingPopularState.data?.isNotEmpty() == true) {
                    this += HeaderMoreItem(R.string.top_airing, this@HomeFragment, order = Constants.ORDER_BY_POPULAR, status = STATUS_BY_ONGOING)
                    this += HorizontalItem(
                        listData = state.airingPopularState.data,
                        type = Constants.STYLE_SMALLER_RECYCLER
                    )
                }
                if(state.popularState.data?.isNotEmpty() == true) {
                    this += HeaderMoreItem(R.string.popular, this@HomeFragment, order = Constants.ORDER_BY_POPULAR, status = null)
                    this += HorizontalItem(
                        listData = state.popularState.data,
                        type = Constants.STYLE_SMALLER_RECYCLER
                    )
                }
                if(state.announcesState.data?.isNotEmpty() == true) {
                    this += HeaderMoreItem(R.string.announces, this@HomeFragment, order = ORDER_BY_POPULAR, status = STATUS_BY_ANONS)
                    this += HorizontalItem(
                        listData = state.announcesState.data,
                        type = Constants.STYLE_SMALLER_RECYCLER
                    )
                }
            }
            groupAdapter.update(list)

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