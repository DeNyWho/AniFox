package com.example.anifox.domain.useCase.detail

import com.example.anifox.data.repository.MangaRepository
import com.example.anifox.domain.model.manga.toData
import com.example.anifox.presentation.detail.state.ContentDetailsState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetDetailsUseCase @Inject constructor(
    private val repository: MangaRepository
) {
    operator fun invoke(id: Int): Flow<ContentDetailsState> {
        return flow {
            emit(ContentDetailsState(isLoading = true))
            val res = repository.getMangaByIdRu(id)

            if (res.isSuccessful){
                val data = res.body()?.data?.map { it.toData() }.orEmpty()
                val state = ContentDetailsState(data = data[0], isLoading = false)
                emit(state)
            } else {
                val state = ContentDetailsState(isLoading = false, error = res.message())
                emit(state)

            }

        }.flowOn(Dispatchers.IO)
    }
}