package com.example.anifox.presentation.search.state

import com.example.anifox.presentation.search.state.random.RandomSearchState
import com.example.anifox.presentation.search.state.search.SearcherState
import com.example.anifox.presentation.search.state.status.OnFinalSearchState
import com.example.anifox.presentation.search.state.status.OnGoingSearchState

data class SearchState (
    val randomState: RandomSearchState,
    val search: SearcherState,
    val onGoing: OnGoingSearchState,
    val onFinal: OnFinalSearchState,
)