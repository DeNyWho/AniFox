package com.example.anifox.adapters

import android.view.View
import androidx.annotation.StringRes
import com.example.anifox.R
import com.example.anifox.databinding.HeaderItemBinding
import com.xwray.groupie.viewbinding.BindableItem

open class HeaderItem(
    @StringRes private val titleStringResId: Int
): BindableItem<HeaderItemBinding>() {

    override fun getLayout(): Int {
        return R.layout.header_item
    }

    override fun initializeViewBinding(view: View): HeaderItemBinding {
        return HeaderItemBinding.bind(view)
    }

    override fun bind(viewBinding: HeaderItemBinding, position: Int) {
        viewBinding.tvName.setText(titleStringResId)
    }
}

