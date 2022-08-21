package com.example.anifox.adapters

import android.util.TypedValue
import android.view.View
import androidx.core.content.ContextCompat
import com.example.anifox.R
import com.example.anifox.databinding.CardItemGenresBinding
import com.example.anifox.domain.model.common.GenresCard
import com.example.anifox.presentation.home.listeners.ItemClickListenerMorePageGenres
import com.xwray.groupie.viewbinding.BindableItem

class SmallGenresItem(var genre: GenresCard, private val onClick: ItemClickListenerMorePageGenres): BindableItem<CardItemGenresBinding>() {
    override fun bind(binding: CardItemGenresBinding, position: Int) {

        binding.tvTitle.text = genre.title
        binding.cardView.setCardBackgroundColor(ContextCompat.getColor(binding.root.context, genre.color))
        binding.card.setCardBackgroundColor(ContextCompat.getColor(binding.root.context, genre.color))
        binding.tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, 35F)
        binding.ivImage.setBackgroundResource(genre.image)
        binding.ivImage.layoutParams.height = genre.imageHeight
        binding.ivImage.layoutParams.width = genre.imageWidth

        binding.root.setOnClickListener {
            onClick.navigationToMorePagesGenres(genre = genre.title)
        }
    }

    override fun getLayout(): Int {
        return R.layout.card_item_genres
    }

    override fun initializeViewBinding(view: View): CardItemGenresBinding {
        return CardItemGenresBinding.bind(view)
    }
}
