package com.example.anifox.adapters

import android.view.View
import com.bumptech.glide.Glide
import com.example.anifox.R
import com.example.anifox.databinding.RankingItemFinalBinding
import com.example.anifox.domain.model.manga.Manga
import com.xwray.groupie.viewbinding.BindableItem

class RankingSimpleItem(var list: Manga): BindableItem<RankingItemFinalBinding>() {
    override fun bind(binding: RankingItemFinalBinding, position: Int) {

        when(position){
            0 -> {
                binding.ivRate.setImageResource(R.drawable.gold_medal)
            }
            1 -> {
                binding.ivRate.setImageResource(R.drawable.seilver_metal)
            }
            2 -> {
                binding.ivRate.setImageResource(R.drawable.bronze_metal)
            }
            3 -> {
                binding.ivRate.visibility = View.GONE
                binding.tvRate.visibility = View.VISIBLE
                binding.tvRate.text = "4"
            }
            4 -> {
                binding.ivRate.visibility = View.GONE
                binding.tvRate.visibility = View.VISIBLE
                binding.tvRate.text = "5"            }
        }

        binding.tvTitle.text = list.title
        println("DATA = ${list.views} ${list.description}")
        binding.tvDescription.text = list.description
        binding.tvCountViews.text = "\uD83D\uDD25${list.views}"
        Glide
            .with(binding.root.context)
            .load(list.image)
            .into(binding.ivImage)

    }

    override fun getLayout(): Int {
        return R.layout.ranking_item_final
    }

    override fun initializeViewBinding(view: View): RankingItemFinalBinding {
        return RankingItemFinalBinding.bind(view)
    }
}
