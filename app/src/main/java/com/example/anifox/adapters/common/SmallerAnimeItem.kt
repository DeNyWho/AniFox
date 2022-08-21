package com.example.anifox.adapters.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.anifox.databinding.CardItemAnimeSmallerBinding
import com.example.anifox.domain.model.manga.Manga

class SmallerAnimeItem : PagingDataAdapter<Manga, SmallerAnimeItemViewHolder>(
    ArticleDiffItemCallback
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SmallerAnimeItemViewHolder {
       return  SmallerAnimeItemViewHolder(CardItemAnimeSmallerBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: SmallerAnimeItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class SmallerAnimeItemViewHolder(private val itemBinding: CardItemAnimeSmallerBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(manga: Manga?) {
        itemBinding.tvName.text = manga?.title
        Glide
            .with(itemBinding.root.context)
            .load(manga?.image)
            .into(itemBinding.ivImage)
    }
}

private object ArticleDiffItemCallback : DiffUtil.ItemCallback<Manga>() {

    override fun areItemsTheSame(oldItem: Manga, newItem: Manga): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Manga, newItem: Manga): Boolean {
        return oldItem.title == newItem.title && oldItem.title == newItem.title
    }
}


