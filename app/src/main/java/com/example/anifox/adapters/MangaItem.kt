package com.example.anifox.adapters

import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.anifox.R
import com.example.anifox.databinding.CardItemAnimeBinding
import com.example.anifox.domain.model.manga.Manga
import com.xwray.groupie.viewbinding.BindableItem


class MangaItem(var manga: Manga, var fragment: Fragment): BindableItem<CardItemAnimeBinding>() {
    override fun bind(binding: CardItemAnimeBinding, position: Int) {
        binding.tvName.text = manga.title
        println("Manga url =${manga.image}")

        Glide
            .with(binding.root.context)
                .load(manga.image)
                .into(binding.ivImage)

//        binding.root.setOnClickListener {
//            val bundle = Bundle()
//            bundle.putInt("animeId", manga.id)
//
//            fragment.findNavController().navigate(R.id.detailFragment, bundle)
//        }
    }

    override fun getLayout(): Int {
        return R.layout.card_item_anime
    }

    override fun initializeViewBinding(view: View): CardItemAnimeBinding {
        return CardItemAnimeBinding.bind(view)
    }
}
