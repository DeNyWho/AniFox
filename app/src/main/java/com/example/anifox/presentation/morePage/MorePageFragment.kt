package com.example.anifox.presentation.morePage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.anifox.R
import com.example.anifox.adapters.MorePageAdapter
import com.example.anifox.databinding.FragmentMorePageBinding
import com.example.anifox.util.LifecycleViewPager
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MorePageFragment : Fragment() {
    private val args: MorePageFragmentArgs by navArgs()

    private var _binding: FragmentMorePageBinding? = null
    private val binding get() = _binding!!
    private var mediator: TabLayoutMediator? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMorePageBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val viewModel: MorePageViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        println("order = ${args.order}")
        println("status = ${args.status}")
        println("genre = ${args.genre}")

//        viewModel.setQueriesOnGoing(args.order, args.genre)
//        viewModel.setQueriesOnPopular(args.status, args.genre)
//        viewModel.setQueriesOnCompleted(args.order, args.genre)

        println(viewModel.queries.value)

        initPager()
        setUpTitle()
    }

    private fun setUpTitle(){
        binding.tvTitle.text = if(args.title != binding.root.context.getString(R.string.Genre_All_Genres)) args.title else "Жанры"
        if(args.title != getString(R.string.Genre_All_Genres)){
            binding.recyclerGenres.visibility = View.GONE
        }
    }

    private fun initPager(){
        binding.viewPager.adapter = MorePageAdapter(fragment = this, order = args.order, genre = args.genre)
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