package com.example.anifox.domain.useCase.home

import com.example.anifox.data.repository.MangaRepository
import com.example.anifox.domain.model.manga.toData
import com.example.anifox.presentation.home.state.random.RandomState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

class GetRandomUseCase @Inject constructor(
    private val repository: MangaRepository
) {
    operator fun invoke(genre: String?, order: String?, status: String?, countCard: Int): Flow<RandomState> {
        return flow {
            emit(RandomState(isLoading = true))
            val res = repository.getManga(genre = genre, order = order, status = status, countCard = countCard)

            if (res.isSuccessful){
                val data = res.body()?.data?.map { it.toData() }.orEmpty()
                Timber.d("DATA = $data")
                val state = RandomState(data, isLoading = false)
                emit(state)
            } else {
                val state = RandomState(isLoading = false, error = res.message())
                emit(state)

            }

        }.flowOn(Dispatchers.IO)
    }
}