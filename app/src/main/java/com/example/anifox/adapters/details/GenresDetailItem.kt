package com.example.anifox.adapters.details

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anifox.R
import com.example.anifox.databinding.GenresDetailsItemBinding
import com.example.anifox.domain.model.manga.Manga
import com.example.anifox.presentation.home.listeners.ItemClickListenerMorePage
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.viewbinding.BindableItem

class GenresDetailItem(var manga: Manga, private val onClick: ItemClickListenerMorePage) : BindableItem<GenresDetailsItemBinding>() {
    private val horizontalAdapter by lazy { GroupAdapter <GroupieViewHolder>() }

    override fun getLayout(): Int {
        return R.layout.genres_details_item
    }

    override fun bind(binding: GenresDetailsItemBinding, position: Int) {
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = horizontalAdapter
        }

        val genresList = manga.genres.title

        horizontalAdapter.replaceAll(genresList.map { GenresSmallDetailsItem(it, onClick) })

    }

    override fun initializeViewBinding(view: View): GenresDetailsItemBinding {
        return GenresDetailsItemBinding.bind(view)
    }
}