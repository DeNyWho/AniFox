package com.example.anifox.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anifox.R
import com.example.anifox.adapters.details.DetailTabsItems
import com.example.anifox.adapters.details.GenresDetailItem
import com.example.anifox.adapters.details.HeaderDetailsItem
import com.example.anifox.databinding.FragmentDetailFragmentBinding
import com.example.anifox.presentation.home.listeners.ItemClickListenerMorePage
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private val args: DetailFragmentArgs by navArgs()

    private var _binding: FragmentDetailFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailFragmentViewModel by viewModels()

    private val groupAdapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setQueries(args.animeId)
        viewModel.getDetails()

        observeOnState()
        initRecycler()
        initListeners()

    }

    private fun initListeners(){

        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }


    }

    private fun initRecycler(){
        binding.recycler.adapter = groupAdapter
        binding.recycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    private fun navigationToMorePagesInAdapter(genre: String){
        val bundle = Bundle()
        bundle.putString("genre", genre)

        findNavController().navigate(R.id.action_detailFragment_to_morePageFragment, bundle)
    }

    private fun observeOnState() {
        viewModel.state.onEach { state ->
            val list = mutableListOf<Item<*>>().apply {
                if(state.contentDetailsState.data != null){
                    this += HeaderDetailsItem(state.contentDetailsState.data)
                    this += GenresDetailItem(
                        manga = state.contentDetailsState.data,
                        onClick = object :
                        ItemClickListenerMorePage {
                        override fun navigationToMorePages(genre: String) {
                            navigationToMorePagesInAdapter(genre)
                        }
                    })
                    this += DetailTabsItems(state.contentDetailsState.data)
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