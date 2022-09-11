package com.example.anifox.common.dialogs.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.anifox.common.dialogs.MaterialDialogFragment
import com.example.anifox.databinding.FavouriteDialogBinding

class FavouriteDialogFragment: MaterialDialogFragment() {

    private var _binding: FavouriteDialogBinding? = null
    private val binding get() = _binding!!

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
        binding.tvInPlan.isEnabled = true
        binding.cvExit.setOnClickListener {
            dismiss()
        }
        binding.cvSave.setOnClickListener {

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}