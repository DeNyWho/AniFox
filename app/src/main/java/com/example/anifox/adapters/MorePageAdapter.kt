package com.example.anifox.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.anifox.presentation.morePage.tabs.OnCompletedTab
import com.example.anifox.presentation.morePage.tabs.OnGoingTab
import com.example.anifox.presentation.morePage.tabs.OnPopularTab
import com.example.anifox.util.Constants

class MorePageAdapter(private val fragment: Fragment, private val genre: String?, private val order: String?) : FragmentStateAdapter(fragment.childFragmentManager, fragment.viewLifecycleOwner.lifecycle) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                OnPopularTab(genre = genre)
            }
            1 -> {
                OnGoingTab(genre = genre, order = order)
            }
            2 -> {
                OnCompletedTab(genre = genre, order = order)
            }
            else -> throw IllegalArgumentException(Constants.ILLEGAL_ARGUMENT_FRAGMENT_TYPE)
        }
    }
}