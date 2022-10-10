package com.example.anifox.common.dialogs.reader

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anifox.common.adapters.details.ChaptersItem
import com.example.anifox.common.dialogs.MaterialDialogFragment
import com.example.anifox.common.listeners.ItemClickListenerReaderChapters
import com.example.anifox.databinding.ContentDialogBinding
import com.example.anifox.domain.model.manga.Manga
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContentsDialogFragment(private val manga: Manga, private val onClick: ItemClickListenerReaderChapters): MaterialDialogFragment() {

    private var _binding: ContentDialogBinding? = null
    private val binding get() = _binding!!

    private val groupAdapter = GroupAdapter<GroupieViewHolder>()

    override fun onStart() {
        super.onStart()
        val dialog: Dialog? = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window?.setLayout(width, height)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ContentDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
        initRecycler()
    }


    private fun initRecycler(){
        binding.recycler.adapter = groupAdapter
        binding.recycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    private fun initListeners() {

        binding.ivExit.setOnClickListener {
            dismiss()
        }
        val list = mutableListOf<Item<*>>().apply {
            for( i in 0 until manga.chaptersCount) {
                this += ChaptersItem(
                    manga = manga,
                    title = manga.chapters.title[i],
                    date = manga.chapters.date[i],
                    url = manga.chapters.url[i],
                    onClick = null,
                    onClickReader = onClick
                )
            }
        }

        groupAdapter.update(list)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}