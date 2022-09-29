package com.example.anifox.common.adapters.home

import android.view.View
import androidx.annotation.DrawableRes
import com.example.anifox.R
import com.example.anifox.common.listeners.ItemClickListenerMorePage
import com.example.anifox.databinding.HeaderMoreItemBinding
import com.xwray.groupie.viewbinding.BindableItem

open class HeaderMoreItem(
    private val title: String,
    @DrawableRes private val image: Int,
    private val onClick: ItemClickListenerMorePage
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

        viewBinding.tvSeeMore.setOnClickListener {
            onClick.navigationToMorePages()
        }

        viewBinding.ivImageGoIn.setOnClickListener {
            onClick.navigationToMorePages()
        }
    }
}