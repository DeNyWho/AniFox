package com.example.anifox.adapters

import android.os.Bundle
import android.view.View
import androidx.annotation.DrawableRes
import androidx.navigation.findNavController
import com.example.anifox.R
import com.example.anifox.databinding.HeaderMoreItemBinding
import com.xwray.groupie.viewbinding.BindableItem

open class HeaderMoreItem(
    private val title: String,
    private val order: String?,
    private val status: String?,
    private val genre: String?,
    @DrawableRes private val image: Int
): BindableItem<HeaderMoreItemBinding>() {

    override fun getLayout(): Int {
        return R.layout.header_more_item
    }

    override fun initializeViewBinding(view: View): HeaderMoreItemBinding {
        return HeaderMoreItemBinding.bind(view)
    }

    override fun bind(viewBinding: HeaderMoreItemBinding, position: Int) {
        viewBinding.tvTitle.text = title
        viewBinding.ivImageSmile.setImageResource(image)
        val bundle = Bundle()
        bundle.putString("order", order ?: "")
        bundle.putString("status", status ?: "")
        bundle.putString("title", title)
        bundle.putString("genre", genre)

        viewBinding.tvSeeMore.setOnClickListener {
            viewBinding.root.findNavController().navigate(R.id.action_homeFragment_to_morePageFragment, bundle)
        }

        viewBinding.ivImageGoIn.setOnClickListener {
            viewBinding.root.findNavController().navigate(R.id.action_homeFragment_to_morePageFragment, bundle)
        }
    }
}