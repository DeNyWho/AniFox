package com.example.anifox.adapters.home

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.anifox.R
import com.example.anifox.adapters.common.MangaItem
import com.example.anifox.databinding.RandomizeItemBinding
import com.example.anifox.domain.model.manga.Manga
import com.example.anifox.presentation.home.listeners.ItemClickListenerGoToDetail
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.viewbinding.BindableItem
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator

class RandomizeItem(
    private var listData: List<Manga>,
    private var title: String,
    private var image: Int,
    private val onClick: ItemClickListenerGoToDetail
) : BindableItem<RandomizeItemBinding>() {
    private val randomizeAdapter by lazy { GroupAdapter <GroupieViewHolder>() }

    override fun getLayout(): Int {
        return R.layout.randomize_item
    }

    override fun bind(binding: RandomizeItemBinding, position: Int) {
        binding.tvTitle.text = title
        binding.ivImageSmile.setImageResource(image)
        binding.recycler.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = randomizeAdapter
            itemAnimator = SlideInUpAnimator().apply {
                addDuration = 300
            }
        }

        randomizeAdapter.replaceAll(listData.map { MangaItem(it, onClick) })
    }

    override fun initializeViewBinding(view: View): RandomizeItemBinding {
        return RandomizeItemBinding.bind(view)
    }
}
