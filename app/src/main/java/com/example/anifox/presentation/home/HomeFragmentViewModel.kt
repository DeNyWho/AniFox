package com.example.anifox.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anifox.domain.useCase.home.GetDiscoverAnimeUseCase
import com.example.anifox.domain.useCase.home.GetMostReviewUseCase
import com.example.anifox.domain.useCase.home.GetPopularReviewUseCase
import com.example.anifox.domain.useCase.home.GetTopAiringReviewUseCase
import com.example.anifox.presentation.home.state.HomeState
import com.example.anifox.presentation.home.state.announces.AnnouncesState
import com.example.anifox.presentation.home.state.discover.DiscoverState
import com.example.anifox.presentation.home.state.mostRead.MostReadState
import com.example.anifox.presentation.home.state.popular.AiringPopularAnimeState
import com.example.anifox.presentation.home.state.popular.PopularMangaState
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
    private val getMostRead: GetMostReviewUseCase,
//    private val getTopAnnounces: GetTopAnnouncesReviewUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(
        HomeState(
            airingPopularState = AiringPopularAnimeState(isLoading = true, data = null),
            popularState = PopularMangaState (isLoading = true, data = null),
            announcesState = AnnouncesState(isLoading = true, data = null),
            discoverState = DiscoverState(isLoading = true, data = null),
            mostRead = MostReadState(isLoading = true, data = null)
        )
    )
    val state = _state.asStateFlow()

    fun getMostRead(){
        getMostRead.invoke().onEach { value ->
            _state.tryEmit(
                _state.value.copy(
                    mostRead = _state.value.mostRead.copy(
                        isLoading = false,
                        data = value.data
                    )
                )
            )
        }.launchIn(viewModelScope)
    }

    fun getPopular(){
        getPopular.invoke().onEach { value ->
            _state.tryEmit(
                _state.value.copy(
                    popularState = _state.value.popularState.copy(
                        isLoading = false,
                        data = value.data
                    )
                )
            )
        }.launchIn(viewModelScope)
    }

//    fun getDiscover(){
//        getDiscoverAnime.invoke().onEach { value ->
//            _state.tryEmit(
//                _state.value.copy(
//                    discoverState = _state.value.discoverState.copy(
//                        isLoading = false,
//                        data = value.data
//                    )
//                )
//            )
//        }.launchIn(viewModelScope)
//    }

    fun getPopularAiring(){
        getTopAiring.invoke().onEach { value ->
            _state.tryEmit(
                _state.value.copy(
                    airingPopularState = _state.value.airingPopularState.copy(
                        isLoading = false,
                        data = value.data
                    )
                )
            )
        } .launchIn(viewModelScope)
    }

}