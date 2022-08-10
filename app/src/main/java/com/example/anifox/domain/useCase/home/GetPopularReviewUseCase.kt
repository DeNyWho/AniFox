package com.example.anifox.domain.useCase.home

import com.example.anifox.data.repository.MangaRepository
import com.example.anifox.domain.model.manga.toData
import com.example.anifox.presentation.home.state.popular.PopularMangaState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

class GetPopularReviewUseCase @Inject constructor(
    private val repository: MangaRepository
) {
    operator fun invoke(): Flow<PopularMangaState> {
        return flow {
            emit(PopularMangaState(isLoading = true))
            val res = repository.getMangaByPopularReview()

            if (res.isSuccessful){
                val data = res.body()?.data?.map { it.toData() }.orEmpty()
                Timber.d("DATA = $data")
                val state = PopularMangaState(data, isLoading = false)
                emit(state)
            } else {
                val state = PopularMangaState(isLoading = false, error = res.message())
                emit(state)

            }

        }.flowOn(Dispatchers.IO)
    }
}