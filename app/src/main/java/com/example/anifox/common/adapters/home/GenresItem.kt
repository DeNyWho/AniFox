package com.example.anifox.common.adapters.home

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.anifox.R
import com.example.anifox.databinding.GenresItemBinding
import com.example.anifox.domain.model.common.GenresCard
import com.example.anifox.common.listeners.ItemClickListenerMorePageGenres
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.viewbinding.BindableItem

class GenresItem(
    private val listData: List<GenresCard>,
    private val onClick: ItemClickListenerMorePageGenres
) : BindableItem<GenresItemBinding>() {
    private val gridAdapter by lazy { GroupAdapter <GroupieViewHolder>() }
    override fun getLayout(): Int {
        return R.layout.genres_item
    }

    override fun bind(binding: GenresItemBinding, position: Int) {
        binding.genresRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
            adapter = gridAdapter
        }

        gridAdapter.replaceAll(listData.map { SmallGenresItem(it, onClick) })

    }

    override fun initializeViewBinding(view: View): GenresItemBinding {
        return GenresItemBinding.bind(view)
    }
}

