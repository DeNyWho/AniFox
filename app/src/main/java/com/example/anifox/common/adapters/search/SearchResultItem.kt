package com.example.anifox.common.adapters.search

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.anifox.R
import com.example.anifox.common.adapters.common.MangaItem
import com.example.anifox.common.listeners.ItemClickListenerGoToDetail
import com.example.anifox.databinding.SearchResultItemBinding
import com.example.anifox.domain.model.manga.Manga
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.viewbinding.BindableItem

class SearchResultItem(
    private var listData: List<Manga>,
    private val onClick: ItemClickListenerGoToDetail
) : BindableItem<SearchResultItemBinding>() {
    private val randomizeAdapter by lazy { GroupAdapter <GroupieViewHolder>() }

    override fun getLayout(): Int {
        return R.layout.search_result_item
    }

    override fun bind(binding: SearchResultItemBinding, position: Int) {
        binding.recycler.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = randomizeAdapter
        }

        randomizeAdapter.replaceAll(listData.map { MangaItem(it, onClick) })
    }

    override fun initializeViewBinding(view: View): SearchResultItemBinding {
        return SearchResultItemBinding.bind(view)
    }
}
