package com.example.anifox.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anifox.R
import com.example.anifox.common.adapters.common.HeaderItem
import com.example.anifox.common.adapters.common.HorizontalItem
import com.example.anifox.common.adapters.details.DetailTabsItems
import com.example.anifox.common.adapters.details.GenresDetailItem
import com.example.anifox.common.adapters.details.HeaderDetailsItem
import com.example.anifox.common.dialogs.detail.FavouriteDialogFragment
import com.example.anifox.common.listeners.ItemClickListenerGoToDetail
import com.example.anifox.common.listeners.ItemClickListenerMorePage
import com.example.anifox.databinding.FragmentDetailFragmentBinding
import com.example.anifox.util.Constants
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private val args: DetailFragmentArgs by navArgs()

    private var _binding: FragmentDetailFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailFragmentViewModel by activityViewModels()

    private val groupAdapter = GroupAdapter<GroupieViewHolder>()

    private var token: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getTokenFromPreferences()
        viewModel.setQueries(args.animeId)
        viewModel.getDetails()
        viewModel.getLinked()
        viewModel.getSimilar()

        observeOnState()
        initRecycler()
        initListeners()

    }

    private fun initListeners(){

        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        if(binding.fabButton.visibility == View.VISIBLE) {
            binding.fabButton.setOnClickListener {
                binding.fabButton.background = AppCompatResources.getDrawable(
                    requireContext(),
                    R.drawable.status_dialog_selected
                )
                FavouriteDialogFragment(mangaID = args.animeId, token = token).show(parentFragmentManager, "favouriteDialog")
            }
        }
    }

    private fun navigationToDetailInAdapter(id: Int){
        val bundle = Bundle()
        bundle.putInt("animeId", id)

        findNavController().navigate(R.id.detailFragment, bundle)
    }


    private fun initRecycler(){
        binding.recycler.adapter = groupAdapter
        binding.recycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    private fun navigationToMorePagesInAdapter(genre: String){
        val bundle = Bundle()
        bundle.putString("genre", genre)

        findNavController().navigate(R.id.action_detailFragment_to_morePageFragment, bundle)
    }

    private fun observeOnState() {
        viewModel.state.onEach { state ->
            val list = mutableListOf<Item<*>>().apply {
                if(state.contentDetailsState.data != null){
                    binding.tvTitle.text = state.contentDetailsState.data.title
                    this += HeaderDetailsItem(state.contentDetailsState.data)
                    this += GenresDetailItem(
                        manga = state.contentDetailsState.data,
                        onClick = object : ItemClickListenerMorePage {
                        override fun navigationToMorePages(genre: String) {
                            navigationToMorePagesInAdapter(genre)
                        }
                    })
                    this += DetailTabsItems(
                        manga = state.contentDetailsState.data
                    )
                    if(state.detailLinkedState.data?.isNotEmpty() == true){
                        this += HeaderItem(titleStringResId = R.string.title_linked)
                        this += HorizontalItem(
                            listData = state.detailLinkedState.data,
                            type = Constants.STYLE_SMALLER_RECYCLER,
                            onClick = object : ItemClickListenerGoToDetail {
                                override fun navigationToDetail(id: Int) {
                                    navigationToDetailInAdapter(id)
                                }
                            }
                        )
                    }

                    if(state.detailSimilarState.data?.isNotEmpty() == true){
                        this += HeaderItem(titleStringResId = R.string.title_recommendation)
                        this += HorizontalItem(
                            listData = state.detailSimilarState.data,
                            type = Constants.STYLE_SMALLER_RECYCLER,
                            onClick = object : ItemClickListenerGoToDetail {
                                override fun navigationToDetail(id: Int) {
                                    navigationToDetailInAdapter(id)
                                }
                            }
                        )
                    }

                }
                if(state.detailTokenState.token != null){
                    token = state.detailTokenState.token
                    binding.fabButton.visibility = View.VISIBLE
                } else binding.fabButton.visibility = View.GONE
            }



            groupAdapter.update(list)

        }.launchWhenStarted()
    }

    private fun <T> Flow<T>.launchWhenStarted() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted { collect () }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}