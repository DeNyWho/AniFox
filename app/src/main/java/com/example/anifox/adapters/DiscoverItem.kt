package com.example.anifox.adapters

import android.view.View
import com.bumptech.glide.Glide
import com.example.anifox.R
import com.example.anifox.databinding.CardDiscoverBinding
import com.example.anifox.domain.model.manga.Manga
import com.example.anifox.presentation.home.listeners.ItemClickListenerGoToDetail
import com.xwray.groupie.viewbinding.BindableItem

class DiscoverItem(var manga: Manga, private val onClick: ItemClickListenerGoToDetail): BindableItem<CardDiscoverBinding>() {
    override fun bind(binding: CardDiscoverBinding, position: Int) {
        println(manga.image)
        Glide
            .with(binding.root.context)
            .load(manga.image)
            .into(binding.ivImage)
        binding.root.setOnClickListener {
            onClick.navigationToDetail(manga.id)
        }
    }

    override fun getLayout(): Int {
        return R.layout.card_discover
    }

    override fun initializeViewBinding(view: View): CardDiscoverBinding {
        return CardDiscoverBinding.bind(view)
    }
}
