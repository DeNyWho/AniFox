package com.example.anifox.presentation.myList

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.anifox.R

class MyListScreen : Fragment() {

    companion object {
        fun newInstance() = MyListScreen()
    }

    private lateinit var viewModel: MyListScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_list_screen, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MyListScreenViewModel::class.java)
        // TODO: Use the ViewModel
    }

}