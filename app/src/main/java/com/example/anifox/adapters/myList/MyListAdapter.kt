package com.example.anifox.adapters.myList

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.anifox.presentation.myList.tabs.OnFavouriteAllTab
import com.example.anifox.presentation.myList.tabs.OnFavouriteCompleteTab
import com.example.anifox.presentation.myList.tabs.OnFavouriteOnHoldTab
import com.example.anifox.presentation.myList.tabs.OnFavouriteWatchingTab
import com.example.anifox.util.Constants

class MyListAdapter(private val fragment: Fragment) : FragmentStateAdapter(fragment.childFragmentManager, fragment.viewLifecycleOwner.lifecycle) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                OnFavouriteAllTab()
            }
            1 -> {
                OnFavouriteWatchingTab()
            }
            2 -> {
                OnFavouriteOnHoldTab()
            }
            3 -> {
                OnFavouriteCompleteTab()
            }
            else -> throw IllegalArgumentException(Constants.ILLEGAL_ARGUMENT_FRAGMENT_TYPE)
        }
    }
}