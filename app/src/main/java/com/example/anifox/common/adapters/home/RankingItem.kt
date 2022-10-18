package com.example.anifox.common.adapters.home

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anifox.R
import com.example.anifox.common.listeners.ItemClickListenerGoToDetail
import com.example.anifox.common.listeners.ItemClickListenerRatingWithStatus
import com.example.anifox.databinding.RankingItemBinding
import com.example.anifox.domain.model.manga.Manga
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.viewbinding.BindableItem

class RankingItem(
    private val listData: List<Manga>,
    private val onClick: ItemClickListenerGoToDetail,
    private val onClickRating: ItemClickListenerRatingWithStatus,
    private val status: String
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

        binding.abRatingMore.setOnClickListener {
            onClickRating.navigationToMorePagesWithGenre(status)
        }

        verticalAdapter.replaceAll(listData.map { RankingSimpleItem(it, onClick) })
    }

    override fun initializeViewBinding(view: View): RankingItemBinding {
        return RankingItemBinding.bind(view)
    }
}
