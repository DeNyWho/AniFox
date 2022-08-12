package com.example.anifox.adapters

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.navigation.findNavController
import com.example.anifox.R
import com.example.anifox.databinding.HeaderMoreItemBinding
import com.xwray.groupie.viewbinding.BindableItem

open class HeaderMoreItem(
    @StringRes private val titleStringResId: Int,
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
        val bundle = Bundle()
        bundle.putString("order", order ?: "")
        bundle.putString("status", status ?: "")

        viewBinding.tvSeeMore.setOnClickListener {
            viewBinding.root.findNavController().navigate(R.id.action_homeFragment_to_morePageFragment, bundle)
        }

        viewBinding.ivImage.setOnClickListener {
            viewBinding.root.findNavController().navigate(R.id.action_homeFragment_to_morePageFragment, bundle)
        }
    }
}