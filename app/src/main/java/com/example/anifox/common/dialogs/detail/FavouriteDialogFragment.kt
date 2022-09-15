package com.example.anifox.common.dialogs.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.anifox.common.dialogs.MaterialDialogFragment
import com.example.anifox.databinding.FavouriteDialogBinding
import com.example.anifox.presentation.detail.DetailFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class FavouriteDialogFragment(private val mangaID: Int, private val token: String): MaterialDialogFragment() {

    private var _binding: FavouriteDialogBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailFragmentViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FavouriteDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        observeOnState()

    }

    private fun observeOnState() {
        viewModel.state.onEach { state ->
            if (state.detailFavouriteState.data != null) {
                dismiss()
            }
        }.launchWhenStarted()
    }

    private fun initListeners() {
        binding.tvInPlan.isEnabled = true
        binding.cvExit.setOnClickListener {
            dismiss()
        }
        binding.cvSave.setOnClickListener {
            var status = ""
            if(binding.tvInComplete.isEnabled) status = "completed"
            if(binding.tvWatching.isEnabled) status = "watching"
            if(binding.tvInPlan.isEnabled) status = "holdOn"

            viewModel.addFavourite(id = mangaID, status = status, token = token )
        }
        binding.tvInComplete.setOnClickListener {
            binding.tvInPlan.isEnabled = false
            binding.tvInComplete.isEnabled = true
            binding.tvWatching.isEnabled = false
        }

        binding.tvInPlan.setOnClickListener {
            binding.tvInPlan.isEnabled = true
            binding.tvInComplete.isEnabled = false
            binding.tvWatching.isEnabled = false
        }

        binding.tvWatching.setOnClickListener {
            binding.tvInPlan.isEnabled = false
            binding.tvInComplete.isEnabled = false
            binding.tvWatching.isEnabled = true
        }
    }

    private fun <T> Flow<T>.launchWhenStarted() {
        parentFragment?.viewLifecycleOwner?.lifecycleScope?.launchWhenStarted { collect () }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}