package com.example.anifox.adapters.details

import android.view.View
import com.example.anifox.R
import com.example.anifox.databinding.GenresDetailsSmallItemBinding
import com.xwray.groupie.viewbinding.BindableItem

class GenresSmallDetailsItem(var genre: String): BindableItem<GenresDetailsSmallItemBinding>() {
    override fun bind(binding: GenresDetailsSmallItemBinding, position: Int) {
        binding.tvGenre.text = genre
    }

    override fun getLayout(): Int {
        return R.layout.genres_details_small_item
    }

    override fun initializeViewBinding(view: View): GenresDetailsSmallItemBinding {
        return GenresDetailsSmallItemBinding.bind(view)
    }
}
