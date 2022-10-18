package com.example.anifox.common.adapters.common

import android.view.View
import com.bumptech.glide.Glide
import com.example.anifox.R
import com.example.anifox.common.listeners.ItemClickListenerGoToDetail
import com.example.anifox.databinding.CardItemAnimeSmallBinding
import com.example.anifox.domain.model.manga.Manga
import com.xwray.groupie.viewbinding.BindableItem

class SmallMangaItem(var manga: Manga, private val onClick: ItemClickListenerGoToDetail): BindableItem<CardItemAnimeSmallBinding>() {
    override fun bind(binding: CardItemAnimeSmallBinding, position: Int) {
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
        return R.layout.card_item_anime_small
    }

    override fun initializeViewBinding(view: View): CardItemAnimeSmallBinding {
        return CardItemAnimeSmallBinding.bind(view)
    }
}
