package com.example.anifox.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.ahmadhamwi.tabsync.TabbedListMediator
import com.example.anifox.R
import com.example.anifox.databinding.RankingItemsBinding
import com.example.anifox.domain.model.manga.Manga
import com.google.android.material.internal.ViewUtils.dpToPx
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import com.xwray.groupie.viewbinding.BindableItem

class RankingItems(
    private val listData1: List<Manga>,
    private val listData2: List<Manga>,
    private val listData3: List<Manga>,
    private val listData4: List<Manga>
) : BindableItem<RankingItemsBinding>() {
    private val horizontalAdapter by lazy { GroupAdapter <GroupieViewHolder>() }

    override fun getLayout(): Int {
        return R.layout.ranking_items
    }

    override fun bind(binding: RankingItemsBinding, position: Int) {

        binding.recycler.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = horizontalAdapter
        }

        val list = mutableListOf<Item<*>>().apply {
            this += RankingItem(listData1)
            this += RankingItem(listData2)
            this += RankingItem(listData3)
            this += RankingItem(listData4)
        }
        if(binding.tabLayout.tabCount == 0){
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText(R.string.views))
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText(R.string.popular))
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText(R.string.ongoing))
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText(R.string.completed))
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

        val tabs = binding.tabLayout.getChildAt(0) as ViewGroup
        for (i in 0 until tabs.childCount -1) {
            val tab = tabs.getChildAt(i)
            val layoutParams = tab.layoutParams as LinearLayout.LayoutParams
            layoutParams.marginEnd = dpToPx(binding.root.context, 8).toInt()
            tab.layoutParams = layoutParams
            binding.tabLayout.requestLayout()
        }
    }

    override fun initializeViewBinding(view: View): RankingItemsBinding {
        return RankingItemsBinding.bind(view)
    }
}
