package com.example.anifox.presentation.search.state

import com.example.anifox.presentation.search.state.random.RandomSearchState
import com.example.anifox.presentation.search.state.search.SearcherState

data class SearchState (
    val randomState: RandomSearchState,
    val search: SearcherState,
)