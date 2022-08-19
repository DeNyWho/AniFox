package com.example.anifox.adapters

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.anifox.R
import com.example.anifox.databinding.RandomizeItemBinding
import com.example.anifox.domain.model.manga.Manga
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.viewbinding.BindableItem

class RandomizeItem(
    private val listData: List<Manga>
) : BindableItem<RandomizeItemBinding>() {
    private val randomizeAdapter by lazy { GroupAdapter <GroupieViewHolder>() }

    override fun getLayout(): Int {
        return R.layout.randomize_item
    }

    override fun bind(binding: RandomizeItemBinding, position: Int) {
        binding.recycler.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = randomizeAdapter
        }

        randomizeAdapter.replaceAll(listData.map { MangaItem(it)})

    }

    override fun initializeViewBinding(view: View): RandomizeItemBinding {
        return RandomizeItemBinding.bind(view)
    }
}
