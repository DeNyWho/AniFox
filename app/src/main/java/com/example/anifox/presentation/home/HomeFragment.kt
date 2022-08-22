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
import com.example.anifox.adapters.home.*
import com.example.anifox.databinding.FragmentHomeFragmentBinding
import com.example.anifox.domain.model.common.GenresCard
import com.example.anifox.presentation.home.listeners.ItemClickListenerGoToDetail
import com.example.anifox.presentation.home.listeners.ItemClickListenerMorePage
import com.example.anifox.presentation.home.listeners.ItemClickListenerMorePageGenres
import com.example.anifox.presentation.home.listeners.ItemClickListenerRandom
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
open class HomeFragment : Fragment() {

    private var _binding: FragmentHomeFragmentBinding? = null
    private val binding get() = _binding!!

    private val groupAdapter = GroupAdapter<GroupieViewHolder>()

    private val viewModel: HomeViewModel by viewModels()

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

    private fun navigationToMorePagesInAdapter(genre: String){
        val bundle = Bundle()
        bundle.putString("genre", genre)

        findNavController().navigate(R.id.action_homeFragment_to_morePageFragment, bundle)
    }

    private fun navigationToDetailInAdapter(id: Int){
        val bundle = Bundle()
        bundle.putInt("animeId", id)

        findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)
    }

    private fun navigationToMorePagesGenresInAdapter(genre: String){
        val bundle = Bundle()
        bundle.putString("genre", genre)

        findNavController().navigate(R.id.action_homeFragment_to_morePageFragment, bundle)

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
    private fun randomizing(){
        viewModel.getRandom()
    }
    private fun initRecycler(){
        binding.HomeRecycler.adapter = groupAdapter
        binding.HomeRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    private fun observeOnState() = viewModel.state.onEach { state ->
        val list: List<Group> = mutableListOf<Group>().apply {

            if(state.airingPopularState.data?.isNotEmpty() == true) {
                this += Section( HorizontalItem(
                    listData = state.airingPopularState.data,
                    type = Constants.STYLE_BIGGER_RECYCLER,
                    onClick = object : ItemClickListenerGoToDetail {
                        override fun navigationToDetail(id: Int) {
                            navigationToDetailInAdapter(id)
                        }
                    }
                ))
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
                            imageHeight = 60,
                            imageWidth = 60,
                        )
                    ),
                    onClick = object : ItemClickListenerMorePageGenres {
                        override fun navigationToMorePagesGenres(genre: String) {
                            navigationToMorePagesGenresInAdapter(
                                genre = genre
                            )
                        }
                    }
                )
            }
            if(state.magic.data?.isNotEmpty() == true) {
                this += HeaderMoreItem(
                    image = R.drawable.book,
                    title = requireContext().getString(R.string.title_Magic),
                    onClick = object : ItemClickListenerMorePage {
                        override fun navigationToMorePages(genre: String) {
                            navigationToMorePagesInAdapter(genre)
                        }
                    }
                )
                this += HorizontalItem(
                    listData = state.magic.data,
                    type = Constants.STYLE_SMALLER_RECYCLER,
                    onClick = object : ItemClickListenerGoToDetail {
                        override fun navigationToDetail(id: Int) {
                            navigationToDetailInAdapter(id)
                        }
                    }
                )
            }
            if(state.monsters.data?.isNotEmpty() == true) {
                this += HeaderMoreItem(
                    title = requireContext().getString(R.string.Genre_Monsters),
                    image = R.drawable.monster,
                    onClick = object : ItemClickListenerMorePage {
                        override fun navigationToMorePages(genre: String) {
                            navigationToMorePagesInAdapter(
                                genre = requireContext().getString(R.string.Genre_Monsters)
                            )
                        }
                    }
                )
                this += HorizontalItem(
                    listData = state.monsters.data,
                    type = Constants.STYLE_SMALLER_RECYCLER,
                    onClick = object : ItemClickListenerGoToDetail {
                        override fun navigationToDetail(id: Int) {
                            navigationToDetailInAdapter(id)
                        }
                    }
                )
            }
            if(state.middleAgesState.data?.isNotEmpty() == true) {
                this += HeaderMoreItem(
                    title = requireContext().getString(R.string.Genre_MiddleAges),
                    image = R.drawable.knight,
                    onClick = object : ItemClickListenerMorePage {
                        override fun navigationToMorePages(genre: String) {
                            navigationToMorePagesInAdapter(
                                genre = requireContext().getString(R.string.Genre_MiddleAges)
                            )
                        }
                    }

                )
                this += HorizontalItem(
                    listData = state.middleAgesState.data,
                    type = Constants.STYLE_SMALLER_RECYCLER,
                    onClick = object : ItemClickListenerGoToDetail {
                        override fun navigationToDetail(id: Int) {
                            navigationToDetailInAdapter(id)
                        }
                    }
                )
            }

            if(state.randomState.data != null) {
                println(state.randomState.data)
                this += RandomizeItem(
                    title = requireContext().getString(R.string.Randomize),
                    image = R.drawable.map,
                    listData = state.randomState.data,
                    onClick = object : ItemClickListenerGoToDetail {
                        override fun navigationToDetail(id: Int) {
                            navigationToDetailInAdapter(id)
                        }
                    },
                    headerClick = object : ItemClickListenerRandom {
                        override fun randomize() {
                            viewModel.getRandom()
                        }
                    }
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
        }

        groupAdapter.replaceAll (list)

    }.launchWhenStarted()

    private fun <T> Flow<T>.launchWhenStarted() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted { collect () }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}