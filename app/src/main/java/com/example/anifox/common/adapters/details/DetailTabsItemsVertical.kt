package com.example.anifox.common.adapters.details

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anifox.R
import com.example.anifox.databinding.DetailTabsItemsVerticalBinding
import com.example.anifox.domain.model.manga.Manga
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import com.xwray.groupie.viewbinding.BindableItem

class DetailTabsItemsVertical(
    private val manga: Manga,
    private var type: Int
) : BindableItem<DetailTabsItemsVerticalBinding>() {
    private val verticalAdapter by lazy { GroupAdapter <GroupieViewHolder>() }

    override fun getLayout(): Int {
        return R.layout.detail_tabs_items_vertical
    }

    override fun bind(binding: DetailTabsItemsVerticalBinding, position: Int) {
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = verticalAdapter
        }

        val list = mutableListOf<Item<*>>().apply {
            this += DetailsTabsItem(manga, type)
        }


        verticalAdapter.replaceAll(list)
    }

    override fun initializeViewBinding(view: View): DetailTabsItemsVerticalBinding {
        return DetailTabsItemsVerticalBinding.bind(view)
    }
}
