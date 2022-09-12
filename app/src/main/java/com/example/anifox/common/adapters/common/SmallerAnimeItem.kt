package com.example.anifox.common.adapters.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.anifox.databinding.CardItemAnimeSmallerBinding
import com.example.anifox.domain.model.manga.Manga
import com.example.anifox.common.listeners.ItemClickListenerGoToDetail

class SmallerAnimeItem( onClicked: ItemClickListenerGoToDetail ) : PagingDataAdapter<Manga, SmallerAnimeItemViewHolder>(
    ArticleDiffItemCallback
) {
    val onClick = onClicked
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SmallerAnimeItemViewHolder {
       return  SmallerAnimeItemViewHolder(CardItemAnimeSmallerBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: SmallerAnimeItemViewHolder, position: Int) {
        holder.bind(getItem(position), onClick)
    }
}

class SmallerAnimeItemViewHolder(private val itemBinding: CardItemAnimeSmallerBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(manga: Manga?, onClick: ItemClickListenerGoToDetail) {
        itemBinding.tvName.text = manga?.title
        Glide
            .with(itemBinding.root.context)
            .load(manga?.image)
            .into(itemBinding.ivImage)
        itemBinding.root.setOnClickListener {
            onClick.navigationToDetail(manga!!.id)
        }
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


