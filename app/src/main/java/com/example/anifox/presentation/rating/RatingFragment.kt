package com.example.anifox.presentation.rating

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.anifox.R
import com.example.anifox.common.adapters.rating.RatingPageAdapter
import com.example.anifox.databinding.FragmentRatingBinding
import com.example.anifox.util.viewpager.LifecycleViewPager
import com.google.android.material.tabs.TabLayoutMediator

class RatingFragment : Fragment() {

    private var _binding: FragmentRatingBinding? = null
    private val binding get() = _binding!!
    private var mediator: TabLayoutMediator? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRatingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
        initPager()
    }

    private fun initListeners (){
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initPager(){
        binding.viewPager.adapter = RatingPageAdapter(fragment = this)
        viewLifecycleOwner.lifecycle.addObserver(LifecycleViewPager(binding.viewPager))

        mediator = TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            val tabTitles = listOf(
                getString(R.string.views),
                getString(R.string.popular),
                getString(R.string.ongoing),
                getString(R.string.completed)
            )
            tab.text = tabTitles[position]
        }

        mediator?.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}