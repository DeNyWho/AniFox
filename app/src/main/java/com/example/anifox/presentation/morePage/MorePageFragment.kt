package com.example.anifox.presentation.morePage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.anifox.R
import com.example.anifox.common.adapters.morePages.GenresTypeItem
import com.example.anifox.common.adapters.morePages.MorePageAdapter
import com.example.anifox.common.listeners.ItemClickListenerMoreGenre
import com.example.anifox.core.enums.GenreConstants
import com.example.anifox.databinding.FragmentMorePageBinding
import com.example.anifox.util.viewpager.LifecycleViewPager
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MorePageFragment : Fragment() {
    private val args: MorePageFragmentArgs by navArgs()

    private var _binding: FragmentMorePageBinding? = null
    private val binding get() = _binding!!
    private var mediator: TabLayoutMediator? = null

    private val genreAdapter = GenresTypeItem(onClick = object : ItemClickListenerMoreGenre {
        override fun newGenre(genre: String) {
            setNewGenre(genre)
        } }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMorePageBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val viewModel: MorePageViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getGenres()
        viewModel.setQueriesOnPopular(genre = if(args.genre != getString(R.string.Genre_All_Genres)) args.genre else null)
        viewModel.setQueriesOnGoing(genre = if(args.genre != getString(R.string.Genre_All_Genres)) args.genre else null)
        viewModel.setQueriesOnCompleted(genre = if(args.genre != getString(R.string.Genre_All_Genres)) args.genre else null)

        initListeners()
        initPager()
        initRecycler()
        setUpTitle()
    }

    private fun setNewGenre(genre: String) {
        viewModel.setQueriesOnPopular(genre = genre)
        viewModel.setQueriesOnGoing(genre = genre)
        viewModel.setQueriesOnCompleted(genre = genre)

        viewModel.getMangasOnGoingPaging()
        viewModel.getMangasOnPopularPaging()
        viewModel.getMangasOnCompletedPaging()
        binding.viewPager.adapter = MorePageAdapter(fragment = this)
    }

    private fun initRecycler() {
        binding.recyclerGenres.apply {
            layoutManager = StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL)
        }
        val genreList = GenreConstants.values().map {
            it.toReadableName(requireContext())
        }
        genreAdapter.submitList(genreList)

        binding.recyclerGenres.adapter = genreAdapter

    }

    private fun initListeners(){
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.ivSearch.setOnClickListener {
            findNavController().navigate(R.id.action_morePageFragment_to_searchFragment)
        }

        binding.ivFilter.setOnClickListener {
            if(binding.recyclerGenres.visibility == View.GONE) {
                binding.recyclerGenres.visibility = View.VISIBLE
            } else binding.recyclerGenres.visibility = View.GONE
        }

    }

    private fun setUpTitle(){
        binding.tvTitle.text = if(args.genre != getString(R.string.Genre_All_Genres)) args.genre else "Жанры"
        if(args.genre != getString(R.string.Genre_All_Genres)){
            binding.recyclerGenres.visibility = View.GONE
        }
    }

    private fun initPager(){
        binding.viewPager.adapter = MorePageAdapter(fragment = this)
        viewLifecycleOwner.lifecycle.addObserver(LifecycleViewPager(binding.viewPager))

        mediator = TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            val tabTitles = listOf(getString(R.string.tab_status_popular),getString(R.string.tab_status_airing), getString(R.string.tab_status_completed))
            tab.text = tabTitles[position]
        }

        mediator?.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}