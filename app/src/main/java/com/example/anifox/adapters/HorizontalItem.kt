package com.example.anifox.adapters

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anifox.R
import com.example.anifox.databinding.HorizontalItemBinding
import com.example.anifox.domain.model.anime.Anime
import com.example.anifox.util.Constants
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.viewbinding.BindableItem

class HorizontalItem(
    private val listData: List<Anime>,
    private val type: String
) : BindableItem<HorizontalItemBinding>() {
    private val horizontalAdapter by lazy { GroupAdapter <GroupieViewHolder>() }
    override fun getLayout(): Int {
        return R.layout.horizontal_item
    }

    override fun bind(binding: HorizontalItemBinding, position: Int) {
        binding.horizontalRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = horizontalAdapter
        }
        if (type == Constants.STYLE_BIGGER_RECYCLER) {
            horizontalAdapter.replaceAll(listData.map { DiscoverItem(it) })
        }
        else {
            horizontalAdapter.replaceAll(listData.map { AnimeItem(it) })
        }
    }

    override fun initializeViewBinding(view: View): HorizontalItemBinding {
        return HorizontalItemBinding.bind(view)
    }
}
