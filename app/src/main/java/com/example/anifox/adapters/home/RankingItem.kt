package com.example.anifox.adapters.home

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anifox.R
import com.example.anifox.databinding.RankingItemBinding
import com.example.anifox.domain.model.manga.Manga
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.viewbinding.BindableItem

class RankingItem(
    private val listData: List<Manga>
) : BindableItem<RankingItemBinding>() {
    private val verticalAdapter by lazy { GroupAdapter <GroupieViewHolder>() }

    override fun getLayout(): Int {
        return R.layout.ranking_item
    }

    override fun bind(binding: RankingItemBinding, position: Int) {
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = verticalAdapter
        }

        verticalAdapter.replaceAll(listData.map { RankingSimpleItem(it) })
    }

    override fun initializeViewBinding(view: View): RankingItemBinding {
        return RankingItemBinding.bind(view)
    }
}
