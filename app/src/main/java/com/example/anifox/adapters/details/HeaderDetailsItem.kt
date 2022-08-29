package com.example.anifox.adapters.details

import android.view.View
import com.bumptech.glide.Glide
import com.example.anifox.R
import com.example.anifox.databinding.HeaderDetailsItemBinding
import com.example.anifox.domain.model.manga.Manga
import com.xwray.groupie.viewbinding.BindableItem

class HeaderDetailsItem(var manga: Manga): BindableItem<HeaderDetailsItemBinding>() {
    override fun bind(binding: HeaderDetailsItemBinding, position: Int) {
        binding.tvName.text = "${manga.title} "
        binding.tvRating.text = "${manga.rate}⭐"
        Glide
            .with(binding.root.context)
            .load(manga.image)
            .into(binding.ivAnime)

        binding.tvType.text = "${manga.types.type} "
        binding.tvYear.text = "${manga.types.year} "
        binding.tvStatus.text = "${manga.types.status} "

    }

    override fun getLayout(): Int {
        return R.layout.header_details_item
    }

    override fun initializeViewBinding(view: View): HeaderDetailsItemBinding {
        return HeaderDetailsItemBinding.bind(view)
    }
}
