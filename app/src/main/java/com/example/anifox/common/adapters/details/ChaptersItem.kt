package com.example.anifox.common.adapters.details

import android.view.View
import com.example.anifox.R
import com.example.anifox.common.listeners.ItemClickListenerGoToReader
import com.example.anifox.databinding.ChaptersItemBinding
import com.xwray.groupie.viewbinding.BindableItem

class ChaptersItem(
    private var date: String,
    private var title: String,
    private var url: String,
    private val onClick: ItemClickListenerGoToReader
): BindableItem<ChaptersItemBinding>() {
    override fun bind(binding: ChaptersItemBinding, position: Int) {
        binding.root.setOnClickListener {
            onClick.navigationToReader(url)
        }
        binding.tvTitle.text = title
        binding.tvDate.text = date
    }

    override fun getLayout(): Int {
        return R.layout.chapters_item
    }

    override fun initializeViewBinding(view: View): ChaptersItemBinding {
        return ChaptersItemBinding.bind(view)
    }
}
