package com.example.anifox.common.adapters.details

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.ahmadhamwi.tabsync.TabbedListMediator
import com.example.anifox.R
import com.example.anifox.databinding.DetailTabsItemsBinding
import com.example.anifox.domain.model.manga.Manga
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import com.xwray.groupie.viewbinding.BindableItem

class DetailTabsItems(
    private val manga: Manga,
) : BindableItem<DetailTabsItemsBinding>() {
    private val horizontalAdapter by lazy { GroupAdapter <GroupieViewHolder>() }

    override fun getLayout(): Int {
        return R.layout.detail_tabs_items
    }

    override fun bind(binding: DetailTabsItemsBinding, position: Int) {

        binding.recycler.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = horizontalAdapter
        }

        val list = mutableListOf<Item<*>>().apply {
            this += DetailTabsItemsVertical(manga, 1)
            this += DetailTabsItemsVertical(manga, 2)
        }
        if(binding.tabLayout.tabCount == 0){
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText(R.string.description))
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText(R.string.chapters))
        }
        binding.recycler.onFlingListener = null
        PagerSnapHelper().attachToRecyclerView(binding.recycler)
        TabbedListMediator(
            binding.recycler,
            binding.tabLayout,
            list.indices.toList(),
            false
        ).attach()
        horizontalAdapter.replaceAll(list)
    }

    override fun initializeViewBinding(view: View): DetailTabsItemsBinding {
        return DetailTabsItemsBinding.bind(view)
    }
}
