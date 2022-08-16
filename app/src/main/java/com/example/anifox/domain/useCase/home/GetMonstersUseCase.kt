package com.example.anifox.domain.useCase.home

import com.example.anifox.data.repository.MangaRepository
import com.example.anifox.domain.model.manga.toData
import com.example.anifox.presentation.home.state.manga.monsters.MonstersState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

class GetMonstersUseCase @Inject constructor(
    private val repository: MangaRepository
) {
    operator fun invoke(genre: String?, order: String?): Flow<MonstersState> {
        return flow {
            emit(MonstersState(isLoading = true))
            val res = repository.getManga(genre = genre, order = order)

            if (res.isSuccessful){
                val data = res.body()?.data?.map { it.toData() }.orEmpty()
                Timber.d("DATA = $data")
                val state = MonstersState(data, isLoading = false)
                emit(state)
            } else {
                val state = MonstersState(isLoading = false, error = res.message())
                emit(state)

            }

        }.flowOn(Dispatchers.IO)
    }
}