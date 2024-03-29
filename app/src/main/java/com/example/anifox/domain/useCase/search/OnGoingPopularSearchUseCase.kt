package com.example.anifox.domain.useCase.search

import com.example.anifox.data.repository.MangaRepository
import com.example.anifox.domain.model.manga.toData
import com.example.anifox.presentation.search.state.status.OnGoingSearchState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class OnGoingPopularSearchUseCase @Inject constructor(
    private val repository: MangaRepository
) {
    operator fun invoke(genre: String?, order: String?, status: String?, countCard: Int): Flow<OnGoingSearchState> {
        return flow {
            emit(OnGoingSearchState(isLoading = true))
            val res = repository.getManga(genre = genre, order = order, status = status, countCard)

            if (res.isSuccessful){
                val data = res.body()?.data?.map { it.toData() }.orEmpty()
                val state = OnGoingSearchState(data, isLoading = false)
                emit(state)
            } else {
                val state = OnGoingSearchState(isLoading = false, error = res.message())
                emit(state)

            }

        }.flowOn(Dispatchers.IO)
    }
}