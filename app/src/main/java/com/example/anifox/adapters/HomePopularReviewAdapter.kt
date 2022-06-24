package com.example.anifox.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.anifox.databinding.CardItemAnimeBinding
import com.example.anifox.domain.model.anime.Anime

class HomePopularReviewAdapter(
    private val animes: List<Anime>
) : RecyclerView.Adapter<CardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = CardItemAnimeBinding.inflate(from, parent, false)
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bindAnime(animes[position])
    }

    override fun getItemCount(): Int = animes.size
}

