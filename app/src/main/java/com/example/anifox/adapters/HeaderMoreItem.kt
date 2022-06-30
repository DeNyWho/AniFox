package com.example.anifox.adapters

import android.view.View
import androidx.annotation.StringRes
import androidx.navigation.fragment.findNavController
import com.example.anifox.R
import com.example.anifox.databinding.HeaderMoreItemBinding
import com.example.anifox.presentation.home.HomeFragment
import com.example.anifox.presentation.home.HomeFragmentDirections
import com.xwray.groupie.viewbinding.BindableItem

open class HeaderMoreItem(
    @StringRes private val titleStringResId: Int,
    private val homeFragment: HomeFragment,
    private val order:  String?,
    private val status: String?
): BindableItem<HeaderMoreItemBinding>() {

    override fun getLayout(): Int {
        return R.layout.header_more_item
    }

    override fun initializeViewBinding(view: View): HeaderMoreItemBinding {
        return HeaderMoreItemBinding.bind(view)
    }

    override fun bind(viewBinding: HeaderMoreItemBinding, position: Int) {
        viewBinding.tvTitle.setText(titleStringResId)

        viewBinding.ivImage.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToMorePageFragment(
                order ?: "",
                status ?: ""
            )
            homeFragment.findNavController().navigate(action)
        }

        viewBinding.tvSeeMore.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToMorePageFragment(
                order ?: "",
                status ?: ""
            )
            homeFragment.findNavController().navigate(action)
        }
    }
}