package com.example.anifox.common.adapters.details

import android.view.View
import com.example.anifox.R
import com.example.anifox.common.listeners.ItemClickListenerMorePageWithGenre
import com.example.anifox.databinding.GenresDetailsSmallItemBinding
import com.xwray.groupie.viewbinding.BindableItem

class GenresSmallDetailsItem(var genre: String, private val onClick: ItemClickListenerMorePageWithGenre): BindableItem<GenresDetailsSmallItemBinding>() {
    override fun bind(binding: GenresDetailsSmallItemBinding, position: Int) {
        binding.root.setOnClickListener {
            onClick.navigationToMorePagesWithGenre(genre)
        }
        binding.tvGenre.text = genre
    }

    override fun getLayout(): Int {
        return R.layout.genres_details_small_item
    }

    override fun initializeViewBinding(view: View): GenresDetailsSmallItemBinding {
        return GenresDetailsSmallItemBinding.bind(view)
    }
}
