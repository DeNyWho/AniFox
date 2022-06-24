package com.example.anifox.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anifox.domain.useCase.home.GetDiscoverAnime
import com.example.anifox.domain.useCase.home.GetNewPopularAnimeUseCase
import com.example.anifox.presentation.home.state.discover.AnimeDiscoverState
import com.example.anifox.presentation.home.state.popular.NewPopularAnimeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val getNewAnimeByPopularReview: GetNewPopularAnimeUseCase,
    private val getDiscoverAnime: GetDiscoverAnime
) : ViewModel() {
    private val _animePopularReview = MutableStateFlow(NewPopularAnimeState())
    val animePopularReview = _animePopularReview.asStateFlow()

    private val _animeDiscover = MutableStateFlow(AnimeDiscoverState())
    val animeDiscover = _animeDiscover.asStateFlow()


    fun getDiscover(){
        getDiscoverAnime.invoke().onEach {
            value -> _animeDiscover.tryEmit(value)
        }.launchIn(viewModelScope)
    }

    fun getPopularReview(){
        getNewAnimeByPopularReview.invoke().onEach {
                value -> _animePopularReview.tryEmit(value)
        } .launchIn(viewModelScope)
    }

}