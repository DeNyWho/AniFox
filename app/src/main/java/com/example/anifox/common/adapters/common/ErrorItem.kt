package com.example.anifox.common.adapters.common

import android.view.View
import com.example.anifox.R
import com.example.anifox.databinding.ErrorItemBinding
import com.xwray.groupie.viewbinding.BindableItem

open class ErrorItem(
    private val errorMessage: String
): BindableItem<ErrorItemBinding>() {

    override fun getLayout(): Int {
        return R.layout.error_item
    }

    override fun initializeViewBinding(view: View): ErrorItemBinding {
        return ErrorItemBinding.bind(view)
    }

    override fun bind(viewBinding: ErrorItemBinding, position: Int) {
        viewBinding.tvError.text = errorMessage

    }
}
