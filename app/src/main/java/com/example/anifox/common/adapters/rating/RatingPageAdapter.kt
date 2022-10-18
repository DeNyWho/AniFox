package com.example.anifox.common.adapters.rating

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.anifox.presentation.rating.tabs.OnRatingCompletedTab
import com.example.anifox.presentation.rating.tabs.OnRatingOngoingTab
import com.example.anifox.presentation.rating.tabs.OnRatingPopularTab
import com.example.anifox.presentation.rating.tabs.OnRatingViewsTab
import com.example.anifox.util.Constants

class RatingPageAdapter(private val fragment: Fragment) : FragmentStateAdapter(fragment.childFragmentManager, fragment.viewLifecycleOwner.lifecycle) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                OnRatingViewsTab()
            }
            1 -> {
                OnRatingPopularTab()
            }
            2 -> {
                OnRatingOngoingTab()
            }
            3 -> {
                OnRatingCompletedTab()
            }
            else -> throw IllegalArgumentException(Constants.ILLEGAL_ARGUMENT_FRAGMENT_TYPE)
        }
    }
}