package com.example.anifox.domain.useCase.detail

import com.example.anifox.data.repository.MangaRepository
import com.example.anifox.domain.model.manga.toDataFull
import com.example.anifox.presentation.detail.state.similar.DetailSimilarState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetSimilarUseCase @Inject constructor(
    private val repository: MangaRepository
) {
    operator fun invoke(id: Int): Flow<DetailSimilarState> {
        return flow {
            emit(DetailSimilarState(isLoading = true))
            val res = repository.getSimilarManga(id)

            if (res.isSuccessful){
                val data = res.body()?.data?.map { it.toDataFull() }.orEmpty()
                val state = DetailSimilarState(data = data, isLoading = false)
                emit(state)
            } else {
                val state = DetailSimilarState(isLoading = false, error = res.message())
                emit(state)

            }

        }.flowOn(Dispatchers.IO)
    }
}