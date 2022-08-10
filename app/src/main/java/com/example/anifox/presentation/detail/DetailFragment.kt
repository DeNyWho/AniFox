package com.example.anifox.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.anifox.databinding.FragmentDetailFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class DetailFragment : Fragment() {
    private val args: DetailFragmentArgs by navArgs()
    private var _binding: FragmentDetailFragmentBinding? = null
    private val binding get() = _binding!!
//    private val viewModel: DetailFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel.setQueries(args.animeId)
//
//        viewModel.getDetails()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}