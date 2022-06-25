package com.example.anifox.adapters

import android.view.View
import com.bumptech.glide.Glide
import com.example.anifox.R
import com.example.anifox.databinding.CardDiscoverBinding
import com.example.anifox.domain.model.anime.Anime
import com.xwray.groupie.viewbinding.BindableItem

class DiscoverItem(var anime: Anime): BindableItem<CardDiscoverBinding>() {
    override fun bind(binding: CardDiscoverBinding, position: Int) {
        Glide.with(binding.root.context).load(anime.image.original).into(binding.animeImage)
    }

    override fun getLayout(): Int {
        return R.layout.card_discover
    }

    override fun initializeViewBinding(view: View): CardDiscoverBinding {
        return CardDiscoverBinding.bind(view)
    }
}
