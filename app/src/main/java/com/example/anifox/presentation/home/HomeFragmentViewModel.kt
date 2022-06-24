package com.example.anifox.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anifox.domain.useCase.home.GetAnimeByPopularReview
import com.example.anifox.presentation.home.state.popular.AnimePopularState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val getAnimeByPopularReview: GetAnimeByPopularReview
) : ViewModel() {

    private val _animePopularReview = MutableStateFlow(AnimePopularState())
    val animePopularReview = _animePopularReview.asStateFlow()

    fun getPopularReview(){
        getAnimeByPopularReview.invoke().onEach {
                value -> _animePopularReview.tryEmit(value)
        } .launchIn(viewModelScope)
    }

//    fun getAnimes(): Flow<PagingData<Anime>> {
//        val newResult: Flow<PagingData<Anime>> =
//            getAnimesUseCase.invoke()
//        currentResult = newResult
//        return newResult
//    }

}