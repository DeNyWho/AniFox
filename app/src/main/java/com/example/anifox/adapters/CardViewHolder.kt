package com.example.anifox.adapters

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.anifox.databinding.CardItemAnimeBinding
import com.example.anifox.domain.model.anime.Anime

class CardViewHolder(
    private val cardItemAnimeBinding: CardItemAnimeBinding
) : RecyclerView.ViewHolder(cardItemAnimeBinding.root) {
    fun bindAnime(anime: Anime) {
        cardItemAnimeBinding.name.text = anime.name
        Glide.with(itemView.context).load("https://shikimori.one${anime.image.original}").into(cardItemAnimeBinding.animeImage)
    }
}