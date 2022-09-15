package com.example.anifox.domain.useCase.detail

import com.example.anifox.data.repository.MangaRepository
import com.example.anifox.domain.model.manga.toDataFull
import com.example.anifox.presentation.detail.state.linked.DetailLinkedState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetLinkedUseCase @Inject constructor(
    private val repository: MangaRepository
) {
    operator fun invoke(id: Int): Flow<DetailLinkedState> {
        return flow {
            emit(DetailLinkedState(isLoading = true))
            val res = repository.getLinkedManga(id)

            if (res.isSuccessful){
                val data = res.body()?.data?.map { it.toDataFull() }.orEmpty()
                val state = DetailLinkedState(data = data, isLoading = false)
                emit(state)
            } else {
                val state = DetailLinkedState(isLoading = false, error = res.message())
                emit(state)

            }

        }.flowOn(Dispatchers.IO)
    }
}