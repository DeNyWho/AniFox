package com.example.anifox.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anifox.domain.useCase.home.GetDiscoverAnimeUseCase
import com.example.anifox.domain.useCase.home.GetPopularReviewUseCase
import com.example.anifox.domain.useCase.home.GetTopAiringReviewUseCase
import com.example.anifox.domain.useCase.home.GetTopAnnouncesReviewUseCase
import com.example.anifox.presentation.home.state.announces.AnnouncesState
import com.example.anifox.presentation.home.state.discover.DiscoverState
import com.example.anifox.presentation.home.state.popular.AiringPopularAnimeState
import com.example.anifox.presentation.home.state.popular.PopularAnimeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val getTopAiring: GetTopAiringReviewUseCase,
    private val getDiscoverAnime: GetDiscoverAnimeUseCase,
    private val getPopular: GetPopularReviewUseCase,
    private val getTopAnnounces: GetTopAnnouncesReviewUseCase
) : ViewModel() {
    private val _animeAiringPopular = MutableStateFlow(AiringPopularAnimeState())
    val animeAiringPopular = _animeAiringPopular.asStateFlow()

    private val _animePopular = MutableStateFlow(PopularAnimeState())
    val animePopular = _animePopular.asStateFlow()

    private val _announcesPopular = MutableStateFlow(AnnouncesState())
    val announcesPopular = _announcesPopular.asStateFlow()

    private val _animeDiscover = MutableStateFlow(DiscoverState())
    val animeDiscover = _animeDiscover.asStateFlow()

    fun getPopular(){
        getPopular.invoke().onEach {
            value -> _animePopular.tryEmit(value)
        }.launchIn(viewModelScope)
    }

    fun getAnnounces(){
        getTopAnnounces.invoke().onEach {
            value -> _announcesPopular.tryEmit(value)
        }.launchIn(viewModelScope)
    }

    fun getDiscover(){
        getDiscoverAnime.invoke().onEach {
            value -> _animeDiscover.tryEmit(value)
        }.launchIn(viewModelScope)
    }

    fun getPopularAiring(){
        getTopAiring.invoke().onEach {
                value -> _animeAiringPopular.tryEmit(value)
        } .launchIn(viewModelScope)
    }

}