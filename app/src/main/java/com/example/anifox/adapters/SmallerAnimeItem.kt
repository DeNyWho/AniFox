package com.example.anifox.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.anifox.databinding.CardItemAnimeSmallerBinding
import com.example.anifox.domain.model.anime.Anime

class SmallerAnimeItem : PagingDataAdapter<Anime, SmallerAnimeItemViewHolder>(ArticleDiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SmallerAnimeItemViewHolder {
       return  SmallerAnimeItemViewHolder(CardItemAnimeSmallerBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: SmallerAnimeItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class SmallerAnimeItemViewHolder(private val itemBinding: CardItemAnimeSmallerBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(anime: Anime?) {
        itemBinding.tvName.text = anime?.name
        Glide
            .with(itemBinding.root.context)
            .load(anime?.image?.original)
            .into(itemBinding.ivImage)
    }
}

private object ArticleDiffItemCallback : DiffUtil.ItemCallback<Anime>() {

    override fun areItemsTheSame(oldItem: Anime, newItem: Anime): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Anime, newItem: Anime): Boolean {
        return oldItem.url == newItem.url && oldItem.name == newItem.name
    }
}


