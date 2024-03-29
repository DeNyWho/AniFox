package com.example.anifox.domain.useCase.home

import com.example.anifox.data.repository.MangaRepository
import com.example.anifox.domain.model.manga.toData
import com.example.anifox.presentation.home.state.manga.middleAges.MiddleAgesState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMiddleAgesUseCase @Inject constructor(
    private val repository: MangaRepository
) {
    operator fun invoke(genre: String?, order: String?, status: String?, countCard: Int): Flow<MiddleAgesState> {
        return flow {
            emit(MiddleAgesState(isLoading = true))
            val res = repository.getManga(genre = genre, order = order, status = status, countCard = countCard)

            if (res.isSuccessful){
                val data = res.body()?.data?.map { it.toData() }.orEmpty()
                val state = MiddleAgesState(data, isLoading = false)
                emit(state)
            } else {
                val state = MiddleAgesState(isLoading = false, error = res.message())
                emit(state)

            }

        }.flowOn(Dispatchers.IO)
    }
}