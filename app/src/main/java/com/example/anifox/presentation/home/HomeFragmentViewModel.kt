package com.example.anifox.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anifox.R
import com.example.anifox.domain.useCase.home.*
import com.example.anifox.presentation.home.state.HomeState
import com.example.anifox.presentation.home.state.announces.AnnouncesState
import com.example.anifox.presentation.home.state.manga.magic.MagicState
import com.example.anifox.presentation.home.state.manga.monsters.MonstersState
import com.example.anifox.presentation.home.state.mostRead.MostReadState
import com.example.anifox.presentation.home.state.popular.AiringPopularAnimeState
import com.example.anifox.presentation.home.state.popular.PopularMangaState
import com.example.anifox.util.StringResourcesProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val stringResourcesProvider: StringResourcesProvider,
    private val getTopAiring: GetTopAiringReviewUseCase,
    private val getPopular: GetPopularReviewUseCase,
    private val getMostRead: GetMostReviewUseCase,
    private val getMonsters: GetMonstersUseCase,
    private val getMagic: GetMagicUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(
        HomeState(
            airingPopularState = AiringPopularAnimeState(isLoading = true, data = null),
            popularState = PopularMangaState (isLoading = true, data = null),
            announcesState = AnnouncesState(isLoading = true, data = null),
            mostRead = MostReadState(isLoading = true, data = null),
            monsters = MonstersState(isLoading = true, data = null),
            magic = MagicState(isLoading = true, data = null),
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

    fun getMagic(){
        getMagic.invoke(genre = stringResourcesProvider.getString(R.string.Genre_Magic), order = null).onEach { value ->
            _state.tryEmit(
                _state.value.copy(
                    magic = _state.value.magic.copy(
                        isLoading = false,
                        data = value.data
                    )
                )
            )
        } .launchIn(viewModelScope)
    }

    fun getMonsters(){
        getMonsters.invoke(genre = stringResourcesProvider.getString(R.string.Genre_Monsters), order = null).onEach { value ->
            _state.tryEmit(
                _state.value.copy(
                    monsters = _state.value.monsters.copy(
                        isLoading = false,
                        data = value.data
                    )
                )
            )
        } .launchIn(viewModelScope)
    }

}

