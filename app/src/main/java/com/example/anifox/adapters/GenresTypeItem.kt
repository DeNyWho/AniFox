package com.example.anifox.adapters

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.navigation.findNavController
import com.example.anifox.R
import com.example.anifox.databinding.ChipGenresBinding
import com.example.anifox.databinding.HeaderMoreItemBinding
import com.xwray.groupie.viewbinding.BindableItem

open class GenresTypeItem(
    private val genre: String?
): BindableItem<ChipGenresBinding>() {

    override fun getLayout(): Int {
        return R.layout.chip_genres
    }

    override fun initializeViewBinding(view: View): ChipGenresBinding {
        return ChipGenresBinding.bind(view)
    }

    override fun bind(viewBinding: ChipGenresBinding, position: Int) {
        viewBinding.tvTitle.text = genre


    }
}