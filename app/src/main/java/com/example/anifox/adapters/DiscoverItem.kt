package com.example.anifox.adapters

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.anifox.R
import com.example.anifox.databinding.CardDiscoverBinding
import com.example.anifox.domain.model.manga.Manga
import com.xwray.groupie.viewbinding.BindableItem

class DiscoverItem(var manga: Manga): BindableItem<CardDiscoverBinding>() {
    override fun bind(binding: CardDiscoverBinding, position: Int) {
        println(manga.image)
        Glide
            .with(binding.root.context)
            .load(manga.image)
            .into(binding.ivImage)
        val bundle = Bundle()
        bundle.putInt("animeId", manga.id)
        binding.root.setOnClickListener (
            Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_detailFragment, bundle)
        )
    }

    override fun getLayout(): Int {
        return R.layout.card_discover
    }

    override fun initializeViewBinding(view: View): CardDiscoverBinding {
        return CardDiscoverBinding.bind(view)
    }
}
