package com.example.anifox.domain.useCase.home

import com.example.anifox.data.repository.MangaRepository
import com.example.anifox.domain.model.manga.toData
import com.example.anifox.presentation.home.state.manga.magic.MagicState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

class GetMagicUseCase @Inject constructor(
    private val repository: MangaRepository
) {
    operator fun invoke(genre: String?, order: String?): Flow<MagicState> {
        return flow {
            emit(MagicState(isLoading = true))
            val res = repository.getManga(genre = genre, order = order)

            if (res.isSuccessful){
                val data = res.body()?.data?.map { it.toData() }.orEmpty()
                Timber.d("DATA = $data")
                val state = MagicState(data, isLoading = false)
                emit(state)
            } else {
                val state = MagicState(isLoading = false, error = res.message())
                emit(state)

            }

        }.flowOn(Dispatchers.IO)
    }
}