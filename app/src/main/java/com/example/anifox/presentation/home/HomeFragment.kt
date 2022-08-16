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
import com.example.anifox.adapters.GenresItem
import com.example.anifox.adapters.HeaderMoreItem
import com.example.anifox.adapters.HorizontalItem
import com.example.anifox.databinding.FragmentHomeFragmentBinding
import com.example.anifox.domain.model.common.GenresCard
import com.example.anifox.util.Constants
import com.example.anifox.util.Constants.SORT_BY_VIEWS
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
//        viewModel.getMostRead()
        viewModel.getMagic()
        viewModel.getMonsters()

        observeOnState()
        initRecycler()

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
                    order = SORT_BY_VIEWS,
                    status = null,
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
                    order = SORT_BY_VIEWS,
                    status = null,
                )
                this += HorizontalItem(
                    listData = state.monsters.data,
                    type = Constants.STYLE_SMALLER_RECYCLER
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