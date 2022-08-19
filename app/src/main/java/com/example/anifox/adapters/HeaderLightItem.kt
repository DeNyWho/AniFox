package com.example.anifox.adapters

import android.view.View
import androidx.annotation.DrawableRes
import com.example.anifox.R
import com.example.anifox.databinding.HeaderLightItemBinding
import com.xwray.groupie.viewbinding.BindableItem

open class HeaderLightItem(
    private val title: String,
    @DrawableRes private val image: Int
): BindableItem<HeaderLightItemBinding>() {

    override fun getLayout(): Int {
        return R.layout.header_light_item
    }

    override fun initializeViewBinding(view: View): HeaderLightItemBinding {
        return HeaderLightItemBinding.bind(view)
    }

    override fun bind(viewBinding: HeaderLightItemBinding, position: Int) {
        viewBinding.tvTitle.text = title
        viewBinding.ivImageSmile.setImageResource(image)
    }
}