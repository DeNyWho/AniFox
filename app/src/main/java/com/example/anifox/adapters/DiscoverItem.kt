package com.example.anifox.adapters

import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.anifox.R
import com.example.anifox.databinding.CardDiscoverBinding
import com.example.anifox.domain.model.manga.Manga
import com.xwray.groupie.viewbinding.BindableItem

class DiscoverItem(var manga: Manga, var fragment: Fragment): BindableItem<CardDiscoverBinding>() {
    override fun bind(binding: CardDiscoverBinding, position: Int) {
        println(manga.image)
        Glide
            .with(binding.root.context)
            .load(manga.image)
            .into(binding.ivImage)

//        binding.root.setOnClickListener {
//            val bundle = Bundle()
//            bundle.putInt("animeUrl", manga.url)
//
//            fragment.findNavController().navigate(R.id.detailFragment, bundle)
//        }
    }

    override fun getLayout(): Int {
        return R.layout.card_discover
    }

    override fun initializeViewBinding(view: View): CardDiscoverBinding {
        return CardDiscoverBinding.bind(view)
    }
}
