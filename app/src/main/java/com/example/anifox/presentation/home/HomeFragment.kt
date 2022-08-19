package com.example.anifox.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anifox.R
import com.example.anifox.adapters.*
import com.example.anifox.databinding.FragmentHomeFragmentBinding
import com.example.anifox.domain.model.common.GenresCard
import com.example.anifox.util.Constants
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

        viewModel.getPopularAiring()
        viewModel.getPopular()
        viewModel.getMagic()
        viewModel.getMonsters()
        viewModel.getMiddleAges()
        viewModel.getMostRead()
        viewModel.getPopularCompleted()
        viewModel.getRandom()

        observeOnState()
        initRecycler()
        initListeners()
    }

    private fun initListeners(){

        binding.ivGenres.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("title", "Все жанры")
            findNavController().navigate(R.id.action_homeFragment_to_morePageFragment, bundle)
        }

        binding.ivSearch.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }

    }

    private fun initRecycler(){
        binding.HomeRecycler.adapter = groupAdapter
        binding.HomeRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    private fun observeOnState() = viewModel.state.onEach { state ->
        val list = mutableListOf<Item<*>>().apply {

            if(state.airingPopularState.data?.isNotEmpty() == true) {
                this += HorizontalItem(
                    listData = state.airingPopularState.data,
                    type = Constants.STYLE_BIGGER_RECYCLER
                )
                this += GenresItem(
                    listData = listOf(
                        GenresCard(
                            title = getString(R.string.Genre_Comedy),
                            image = R.drawable.comedy,
                            color = R.color.greeny
                        ),
                        GenresCard(
                            title = getString(R.string.Genre_Romance),
                            image = R.drawable.heart,
                            color = R.color.pink
                        ),
                        GenresCard(
                            title = getString(R.string.Genre_Fantasy),
                            image = R.drawable.magic,
                            color = R.color.purple_light
                        ),
                        GenresCard(
                            title = getString(R.string.Genre_Horror),
                            image = R.drawable.horrors,
                            color = R.color.bluest
                        ),
                        GenresCard(
                            title = getString(R.string.Genre_Mystic),
                            image = R.drawable.moon,
                            color = R.color.orange_light
                        ),
                        GenresCard(
                            title = getString(R.string.Genre_All_Genres),
                            image = R.drawable.elements,
                            color = R.color.grey_light,
                            imageHeight = 70,
                            imageWidth = 70,
                        )
                    )
                )
            }
            if(state.magic.data?.isNotEmpty() == true) {
                this += HeaderMoreItem(
                    image = R.drawable.book,
                    title = requireContext().getString(R.string.title_Magic),
                    order = Constants.ORDER_BY_POPULAR,
                    status = null,
                    genre = requireContext().getString(R.string.Genre_Magic)
                )
                this += HorizontalItem(
                    listData = state.magic.data,
                    type = Constants.STYLE_SMALLER_RECYCLER
                )
            }
            if(state.monsters.data?.isNotEmpty() == true) {
                this += HeaderMoreItem(
                    image = R.drawable.monster,
                    title = requireContext().getString(R.string.Genre_Monsters),
                    order = Constants.ORDER_BY_POPULAR,
                    status = null,
                    genre = requireContext().getString(R.string.Genre_Monsters)
                )
                this += HorizontalItem(
                    listData = state.monsters.data,
                    type = Constants.STYLE_SMALLER_RECYCLER
                )
            }
            if(state.middleAgesState.data?.isNotEmpty() == true) {
                this += HeaderMoreItem(
                    image = R.drawable.knight,
                    title = requireContext().getString(R.string.Genre_MiddleAges),
                    order = Constants.ORDER_BY_POPULAR,
                    status = null,
                    genre = requireContext().getString(R.string.Genre_MiddleAges)
                )
                this += HorizontalItem(
                    listData = state.middleAgesState.data,
                    type = Constants.STYLE_SMALLER_RECYCLER
                )
            }
            if(state.airingPopularState.data?.isNotEmpty() == true && state.popularState.data?.isNotEmpty() == true && state.mostRead.data?.isNotEmpty() == true && state.popularCompleted.data?.isNotEmpty() == true){
                this += HeaderLightItem(
                    title = requireContext().getString(R.string.Ranking),
                    image = R.drawable.ranking,
                )
                this += RankingItems(
                    state.mostRead.data.take(5),
                    state.popularState.data.take(5),
                    state.airingPopularState.data.take(5),
                    state.popularCompleted.data.take(5),
                )
            }

            if(state.randomState.data?.isNotEmpty() == true ){
                this += HeaderLightItem(
                    title = requireContext().getString(R.string.Randomize),
                    image = R.drawable.map
                )

                this += RandomizeItem(
                    state.randomState.data
                )
            }

        }

        groupAdapter.update(list)

    }.launchWhenStarted()

    private fun <T> Flow<T>.launchWhenStarted() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted { collect () }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}