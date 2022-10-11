package com.example.anifox.common.adapters.details

import android.view.Gravity
import android.view.View
import com.example.anifox.R
import com.example.anifox.common.listeners.ItemClickListenerDialogDismiss
import com.example.anifox.common.listeners.ItemClickListenerGoToReader
import com.example.anifox.common.listeners.ItemClickListenerReaderChapters
import com.example.anifox.databinding.ChaptersItemBinding
import com.example.anifox.domain.model.manga.Manga
import com.xwray.groupie.viewbinding.BindableItem

class ChaptersItem(
    private var date: String,
    private var title: String,
    private var url: String,
    private val onClick: ItemClickListenerGoToReader?,
    private val onClickReader: ItemClickListenerReaderChapters?,
    private val manga: Manga?,
    private val onDismiss: ItemClickListenerDialogDismiss?
): BindableItem<ChaptersItemBinding>() {
    override fun bind(binding: ChaptersItemBinding, position: Int) {
        if(onClick != null) {
            binding.root.setOnClickListener {
                onClick.navigationToReader(url, manga!!)
            }
            binding.tvTitle.text = title
            binding.tvDate.text = date
        } else {
            binding.tvDate.visibility = View.GONE
            binding.tvTitle.gravity = Gravity.START
            binding.tvTitle.text = title
            binding.root.setOnClickListener {
                onClickReader!!.navigationToReader(url)
                onDismiss!!.dialogDismiss()
            }
        }
    }

    override fun getLayout(): Int {
        return R.layout.chapters_item
    }

    override fun initializeViewBinding(view: View): ChaptersItemBinding {
        return ChaptersItemBinding.bind(view)
    }
}
