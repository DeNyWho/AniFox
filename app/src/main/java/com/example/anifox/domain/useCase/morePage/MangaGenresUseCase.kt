package com.example.anifox.domain.useCase.morePage

import com.example.anifox.data.repository.MangaRepository
import com.example.anifox.presentation.morePage.state.genres.OnGenres
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MangaGenresUseCase @Inject constructor(
    private val repository: MangaRepository
) {
    operator fun invoke(): Flow<OnGenres> {
        return flow {
            emit(OnGenres(isLoading = true))
            val res = repository.getGenresManga()

            if (res.isSuccessful){
                val data = res.body()?.data?.map { it }.orEmpty()
                val state = OnGenres(data = data, isLoading = false)
                emit(state)
            } else {
                val state = OnGenres(isLoading = false, error = res.message())
                emit(state)
            }

        }.flowOn(Dispatchers.IO)
    }
}