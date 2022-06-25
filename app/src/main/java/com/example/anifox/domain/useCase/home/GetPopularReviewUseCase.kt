package com.example.anifox.domain.useCase.home

import com.example.anifox.data.repository.AnimeRepository
import com.example.anifox.domain.model.anime.toData
import com.example.anifox.presentation.home.state.popular.PopularAnimeState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

class GetPopularReviewUseCase @Inject constructor(
    private val repository: AnimeRepository
) {
    operator fun invoke(): Flow<PopularAnimeState> {
        return flow {
            emit(PopularAnimeState(isLoading = true))
            val res = repository.getAnimeByPopularReview()

            if (res.isSuccessful){
                val data = res.body()?.map { it.toData() }.orEmpty()
                Timber.d("DATA = $data")
                val state = PopularAnimeState(data, isLoading = false)
                emit(state)
            } else {
                val state = PopularAnimeState(isLoading = false, error = res.message())
                emit(state)

            }

        }.flowOn(Dispatchers.IO)
    }
}