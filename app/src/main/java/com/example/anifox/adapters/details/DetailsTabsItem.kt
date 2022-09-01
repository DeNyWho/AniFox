package com.example.anifox.adapters.details

import android.view.View
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anifox.R
import com.example.anifox.databinding.DetailsTabItemBinding
import com.example.anifox.domain.model.manga.Manga
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import com.xwray.groupie.viewbinding.BindableItem
import kotlin.math.roundToInt


class DetailsTabsItem(private var manga: Manga, private var type: Int): BindableItem<DetailsTabItemBinding>() {
    private val verticalAdapter by lazy { GroupAdapter <GroupieViewHolder>() }

    override fun bind(binding: DetailsTabItemBinding, position: Int) {

        if(type == 1) {
            binding.ccOptionFirst.visibility = View.VISIBLE
            binding.llOptionSecond.visibility = View.GONE
            binding.tvDescription.text = manga.description

            binding.tvDescription.post {
                val lineCount: Int = binding.tvDescription.lineCount
                if (lineCount < 5) {
                    val valueInPixels =
                        binding.root.context.resources.getDimension(R.dimen.marginDescription).roundToInt()
                    val valueInPixelsBottom =
                        binding.root.context.resources.getDimension(R.dimen.marginDescriptionBottom).roundToInt()
                    binding.llOptionFirst.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                        setMargins(valueInPixels, 0, valueInPixels, valueInPixelsBottom)
                    }
                    binding.ivMore.visibility = View.GONE
                }
            }


            binding.ivMore.setOnClickListener {
                if (binding.tvDescription.maxLines == 5) {
                    binding.tvDescription.maxLines = Int.MAX_VALUE
                    binding.ivMore.setImageResource(R.drawable.ic_round_keyboard_arrow_up_24)
                } else {
                    binding.tvDescription.maxLines = 5
                    binding.ivMore.setImageResource(R.drawable.ic_round_keyboard_arrow_down_24)
                }
            }
        } else {
            binding.ccOptionFirst.visibility = View.GONE
            binding.llOptionSecond.visibility = View.VISIBLE

            binding.recycler.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = verticalAdapter
            }

            val list = mutableListOf<Item<*>>().apply {
                for( i in 0 until manga.chaptersCount){
                    this += ChaptersItem(title = manga.chapters.title[i], date = manga.chapters.date[i], url = manga.chapters.url[i] )
                }
            }


            verticalAdapter.replaceAll(list)
        }

    }

    override fun getLayout(): Int {
        return R.layout.details_tab_item
    }

    override fun initializeViewBinding(view: View): DetailsTabItemBinding {
        return DetailsTabItemBinding.bind(view)
    }
}