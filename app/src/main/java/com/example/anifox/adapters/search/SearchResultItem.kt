package com.example.anifox.adapters.search

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anifox.R
import com.example.anifox.adapters.common.MangaItem
import com.example.anifox.databinding.SearchResultItemBinding
import com.example.anifox.domain.model.manga.Manga
import com.example.anifox.presentation.home.listeners.ItemClickListenerGoToDetail
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.viewbinding.BindableItem
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator

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
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = randomizeAdapter
            itemAnimator = SlideInUpAnimator().apply {
                addDuration = 300
            }
        }

        randomizeAdapter.replaceAll(listData.map { MangaItem(it, onClick) })
    }

    override fun initializeViewBinding(view: View): SearchResultItemBinding {
        return SearchResultItemBinding.bind(view)
    }
}
