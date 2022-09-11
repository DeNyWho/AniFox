package com.example.anifox.presentation.myList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.anifox.R
import com.example.anifox.common.adapters.myList.MyListAdapter
import com.example.anifox.databinding.MyListFragmentBinding
import com.example.anifox.util.LifecycleViewPager
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MyListFragment : Fragment() {

    private var _binding: MyListFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MyListViewModel by activityViewModels()

    private var mediator: TabLayoutMediator? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MyListFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getTokenFromPreferences()

        initListeners()
        observeOnState()
        initPager()
    }

    private fun observeOnState() {

        viewModel.state.onEach { state ->
            if (state.tokenState.token == null) {
                binding.warning.visibility = View.VISIBLE
                binding.appBarLayout.visibility = View.GONE
                binding.viewPager.visibility = View.GONE
            } else {
                binding.warning.visibility = View.GONE
                binding.appBarLayout.visibility = View.VISIBLE
                binding.viewPager.visibility = View.VISIBLE
            }

        }.launchWhenStarted()

    }

    private fun initPager(){
        binding.viewPager.adapter = MyListAdapter(fragment = this)
        viewLifecycleOwner.lifecycle.addObserver(LifecycleViewPager(binding.viewPager))

        mediator = TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            val tabTitles = listOf(getString(R.string.tab_list_favourite),getString(R.string.tab_list_watching), getString(R.string.tab_list_onHold), getString(R.string.tab_list_completed))
            tab.text = tabTitles[position]
        }

        mediator?.attach()
    }

    private fun initListeners() {

        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_myListFragment2_to_signUpFragment)
        }
    }

    private fun <T> Flow<T>.launchWhenStarted() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted { collect () }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}