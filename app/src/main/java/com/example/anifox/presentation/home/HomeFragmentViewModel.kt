package com.example.anifox.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anifox.R
import com.example.anifox.domain.useCase.home.*
import com.example.anifox.presentation.home.state.HomeState
import com.example.anifox.presentation.home.state.announces.AnnouncesState
import com.example.anifox.presentation.home.state.manga.magic.MagicState
import com.example.anifox.presentation.home.state.manga.middleAges.MiddleAgesState
import com.example.anifox.presentation.home.state.manga.monsters.MonstersState
import com.example.anifox.presentation.home.state.mostRead.MostReadState
import com.example.anifox.presentation.home.state.popular.AiringPopularAnimeState
import com.example.anifox.presentation.home.state.popular.PopularCompletedState
import com.example.anifox.presentation.home.state.popular.PopularMangaState
import com.example.anifox.presentation.home.state.random.RandomState
import com.example.anifox.util.Constants
import com.example.anifox.util.Constants.ORDER_BY_POPULAR
import com.example.anifox.util.Constants.REVIEW_LIMIT
import com.example.anifox.util.Constants.REVIEW_LIMIT_RANDOMIZE
import com.example.anifox.util.Constants.SORT_BY_RATE
import com.example.anifox.util.Constants.STATUS_BY_FINAL
import com.example.anifox.util.Constants.STATUS_BY_ONGOING
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
    private val getPopularCompleted: GetPopularCompleted,
    private val getTopAiring: GetTopAiringReviewUseCase,
    private val getMiddleAges: GetMiddleAgesUseCase,
    private val getMostRead: GetMostViewReviewUseCase,
    private val getMonsters: GetMonstersUseCase,
    private val getPopular: GetPopularUseCase,
    private val getRandom: GetRandomUseCase,
    private val getMagic: GetMagicUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(
        HomeState(
            airingPopularState = AiringPopularAnimeState(isLoading = true, data = null),
            popularCompleted = PopularCompletedState(isLoading = true, data = null),
            middleAgesState = MiddleAgesState(isLoading = true, data = null),
            popularState = PopularMangaState (isLoading = true, data = null),
            announcesState = AnnouncesState(isLoading = true, data = null),
            mostRead = MostReadState(isLoading = true, data = null),
            monsters = MonstersState(isLoading = true, data = null),
            randomState = RandomState(isLoading = true, data = null),
            magic = MagicState(isLoading = true, data = null),
        )
    )
    val state = _state.asStateFlow()

    fun getPopular(){
        getPopular.invoke(genre = null, order = ORDER_BY_POPULAR, status = null, countCard = REVIEW_LIMIT).onEach { value ->
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
        getTopAiring.invoke(genre = null, order = SORT_BY_RATE, status = STATUS_BY_ONGOING, countCard = REVIEW_LIMIT).onEach { value ->
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


    fun getPopularCompleted(){
        getPopularCompleted.invoke(genre = null, order = ORDER_BY_POPULAR, status = STATUS_BY_FINAL, countCard = REVIEW_LIMIT).onEach { value ->
            _state.tryEmit(
                _state.value.copy(
                    popularCompleted = _state.value.popularCompleted.copy(
                        isLoading = false,
                        data = value.data
                    )
                )
            )
        } .launchIn(viewModelScope)
    }

    fun getMagic(){
        getMagic.invoke(genre = stringResourcesProvider.getString(R.string.Genre_Magic), order = null, status = null, countCard = REVIEW_LIMIT).onEach { value ->
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
        getMonsters.invoke(genre = stringResourcesProvider.getString(R.string.Genre_Monsters), order = null, status = null, countCard = REVIEW_LIMIT).onEach { value ->
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

    fun getMiddleAges(){
        getMiddleAges.invoke(genre = stringResourcesProvider.getString(R.string.Genre_MiddleAges), order = null, status = null, countCard = REVIEW_LIMIT).onEach { value ->
            _state.tryEmit(
                _state.value.copy(
                    middleAgesState = _state.value.middleAgesState.copy(
                        isLoading = false,
                        data = value.data
                    )
                )
            )
        } .launchIn(viewModelScope)
    }

    fun getRandom(){
        getRandom.invoke(genre = stringResourcesProvider.getString(R.string.Genre_Random), order = null, status = null, countCard = REVIEW_LIMIT_RANDOMIZE).onEach { value ->
            _state.tryEmit(
                _state.value.copy(
                    randomState = _state.value.randomState.copy(
                        isLoading = false,
                        data = value.data
                    )
                )
            )
        }.launchIn(viewModelScope)
    }


    fun getMostRead(){
        getMostRead.invoke(genre = null, order = Constants.ORDER_BY_VIEWS, status = null, countCard = REVIEW_LIMIT).onEach { value ->
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


}

