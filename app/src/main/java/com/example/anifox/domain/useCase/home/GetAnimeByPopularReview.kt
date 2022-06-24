package com.example.anifox.domain.useCase.home

import com.example.anifox.data.repository.AnimeRepository
import com.example.anifox.domain.model.anime.toPopular
import com.example.anifox.presentation.home.state.popular.NewPopularAnimeState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

class GetNewPopularAnimeUseCase @Inject constructor(
    private val repository: AnimeRepository
) {
    operator fun invoke(): Flow<NewPopularAnimeState> {
        return flow {
            emit(NewPopularAnimeState(isLoading = true))
            val res = repository.getAnimeByPopularReview()

            if (res.isSuccessful){
                val data = res.body()?.map { it.toPopular() }.orEmpty()
                Timber.d("DATA = $data")
                val state = NewPopularAnimeState(data, isLoading = false)
                emit(state)
            } else {
                val state = NewPopularAnimeState(isLoading = false, error = res.message())
                emit(state)

            }

        }.flowOn(Dispatchers.IO)
    }
}