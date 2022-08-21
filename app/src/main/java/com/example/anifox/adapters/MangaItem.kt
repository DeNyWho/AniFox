package com.example.anifox.adapters

import android.view.View
import com.bumptech.glide.Glide
import com.example.anifox.R
import com.example.anifox.databinding.CardItemAnimeBinding
import com.example.anifox.domain.model.manga.Manga
import com.example.anifox.presentation.home.listeners.ItemClickListenerGoToDetail
import com.xwray.groupie.viewbinding.BindableItem


class MangaItem(var manga: Manga, private val onClick: ItemClickListenerGoToDetail): BindableItem<CardItemAnimeBinding>() {
    override fun bind(binding: CardItemAnimeBinding, position: Int) {
        binding.tvName.text = manga.title

        Glide
            .with(binding.root.context)
            .load(manga.image)
            .into(binding.ivImage)

        binding.root.setOnClickListener {
            onClick.navigationToDetail(manga.id)
        }
    }

    override fun getLayout(): Int {
        return R.layout.card_item_anime
    }

    override fun initializeViewBinding(view: View): CardItemAnimeBinding {
        return CardItemAnimeBinding.bind(view)
    }
}
