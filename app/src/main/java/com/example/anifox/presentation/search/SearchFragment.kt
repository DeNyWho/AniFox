package com.example.anifox.presentation.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anifox.R
import com.example.anifox.common.adapters.common.ErrorItem
import com.example.anifox.common.adapters.common.HeaderLightItem
import com.example.anifox.common.adapters.common.HorizontalItem
import com.example.anifox.common.adapters.search.SearchResultItem
import com.example.anifox.common.listeners.ItemClickListenerGoToDetail
import com.example.anifox.databinding.FragmentSearchBinding
import com.example.anifox.util.Constants
import com.xwray.groupie.Group
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Section
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by viewModels()

    private val groupAdapter = GroupAdapter<GroupieViewHolder>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getRandom()
        viewModel.getPopularOnGoing()
        viewModel.getPopularOnFinal()


        observeOnState()
        initRecycler()
        initListeners()

    }

    private fun navigationToDetailInAdapter(id: Int){
        val bundle = Bundle()
        bundle.putInt("animeId", id)

        findNavController().navigate(R.id.action_searchFragment_to_detailFragment, bundle)
    }


    private fun initListeners(){

        binding.etSearch.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                when{
                    count > 2 -> {
                        binding.tilSearch.isHelperTextEnabled = false
                        viewModel.clearData()
                        viewModel.setQueries(query = s.toString())
                        viewModel.getSearch()
                    }
                    count < 3 -> {
                        binding.tilSearch.helperText = getString(R.string.search_min_size_field)
                        viewModel.getPopularOnGoing()
                        viewModel.getPopularOnFinal()
                        viewModel.clearSearch()
                    }
                }
            }
        })

        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    private fun initRecycler(){
        binding.recycler.adapter = groupAdapter
        binding.recycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }


    private fun observeOnState() {
        viewModel.state.onEach { state ->
            val list: List<Group> = mutableListOf<Group>().apply {
                if(state.randomState.data != null){
                    binding.tilSearch.hint = "${getString(R.string.search_hint)} «${state.randomState.data.title}»"
                }
                if(state.search.data?.isNotEmpty() == true) {
                    println("BASICS = ${state.search.data}")
                    this += HeaderLightItem(
                        image = R.drawable.searcher,
                        title = requireContext().getString(R.string.search_result)
                    )
                    this += Section(
                        SearchResultItem(
                        listData = state.search.data,
                        onClick = object : ItemClickListenerGoToDetail {
                            override fun navigationToDetail(id: Int) {
                                navigationToDetailInAdapter(id)
                            }
                        }
                    )
                    )
                }
                println("WTF STATE  4 = ${state.search}")
                if(state.search.message == "Not Found"){
                    println("WTF STATE = ${state.search}")
                    this += ErrorItem(
                        errorMessage = "${getString(R.string.warning_search_start)}${binding.etSearch.text}${getString(R.string.warning_search_end)}"
                    )
                }
                if(state.onGoing.data?.isNotEmpty() == true) {
                    this += HeaderLightItem(
                        image = R.drawable.ongoing,
                        title = requireContext().getString(R.string.ongoing)
                    )
                    this += HorizontalItem(
                        listData = state.onGoing.data,
                        type = Constants.STYLE_SMALLER_RECYCLER,
                        onClick = object : ItemClickListenerGoToDetail {
                            override fun navigationToDetail(id: Int) {
                                navigationToDetailInAdapter(id)
                            }
                        }
                    )
                }

                if(state.onFinal.data?.isNotEmpty() == true) {
                    this += HeaderLightItem(
                        image = R.drawable.completed,
                        title = requireContext().getString(R.string.completed)
                    )
                    this += HorizontalItem(
                        listData = state.onFinal.data,
                        type = Constants.STYLE_SMALLER_RECYCLER,
                        onClick = object : ItemClickListenerGoToDetail {
                            override fun navigationToDetail(id: Int) {
                                navigationToDetailInAdapter(id)
                            }
                        }
                    )
                }




            }

            groupAdapter.replaceAll(list)

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