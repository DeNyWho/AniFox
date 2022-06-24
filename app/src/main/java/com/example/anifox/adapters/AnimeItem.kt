package com.example.anifox.adapters

import android.view.View
import com.bumptech.glide.Glide
import com.example.anifox.R
import com.example.anifox.databinding.CardItemAnimeBinding
import com.example.anifox.domain.model.anime.Anime
import com.xwray.groupie.viewbinding.BindableItem

class AnimeItem(var anime: Anime): BindableItem<CardItemAnimeBinding>() {
    override fun bind(binding: CardItemAnimeBinding, position: Int) {
        binding.name.text = anime.name
        Glide.with(binding.root.context).load(anime.image.original).into(binding.animeImage)
    }

    override fun getLayout(): Int {
        return R.layout.card_item_anime
    }

    override fun initializeViewBinding(view: View): CardItemAnimeBinding {
        return CardItemAnimeBinding.bind(view)
    }
}
