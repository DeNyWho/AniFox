package com.example.anifox.common.adapters.morePages

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.anifox.R
import com.example.anifox.common.listeners.ItemClickListenerMoreGenre
import com.example.anifox.databinding.ChipGenresBinding

class GenresTypeItem(private val onClick: ItemClickListenerMoreGenre) :
    ListAdapter<String, GenresTypeItem.MainViewHolder>(DIFF_UTIL) {

    private var selectedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.chip_genres, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(getItem(position))

    }

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ChipGenresBinding.bind(itemView)


        fun bind(genre: String) = with(binding) {
            rbChip.text = genre

            rbChip.apply {
                isChecked = selectedPosition == adapterPosition
                setOnClickListener {
                    checkItem()
                }
            }

            itemView.setOnClickListener {
                checkItem()
                onClick.newGenre(genre)
            }


        }
    }

    private fun MainViewHolder.checkItem() {
        if(selectedPosition != -1) notifyItemChanged(selectedPosition)
        selectedPosition = adapterPosition
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }
        }
    }
}