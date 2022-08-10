package com.example.anifox.domain.useCase.home

import com.example.anifox.data.repository.MangaRepository
import com.example.anifox.domain.model.manga.toData
import com.example.anifox.presentation.home.state.mostRead.MostReadState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

class GetMostReviewUseCase @Inject constructor(
    private val repository: MangaRepository
) {
    operator fun invoke(): Flow<MostReadState> {
        return flow {
            emit(MostReadState(isLoading = true))
            val res = repository.getMostReadManga()

            if (res.isSuccessful){
                val data = res.body()?.data?.map { it.toData() }.orEmpty()
                Timber.d("DATA = $data")
                val state = MostReadState(data, isLoading = false)
                emit(state)
            } else {
                val state = MostReadState(isLoading = false, error = res.message())
                emit(state)

            }

        }.flowOn(Dispatchers.IO)
    }
}