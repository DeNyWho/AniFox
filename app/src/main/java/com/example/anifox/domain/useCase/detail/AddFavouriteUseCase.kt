package com.example.anifox.domain.useCase.detail

import com.example.anifox.data.repository.MangaRepository
import com.example.anifox.domain.model.manga.NewFavouriteManga
import com.example.anifox.presentation.detail.state.favourite.DetailFavouriteState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AddFavouriteUseCase @Inject constructor(
    private val repository: MangaRepository
) {
    operator fun invoke(token: String, status: String, id: Int): Flow<DetailFavouriteState> {
        return flow {
            emit(DetailFavouriteState(isLoading = true))
            val res = repository.addFavouriteManga(
                newFavourite = NewFavouriteManga(
                    token = token,
                    mangaId = id
                ), status = status
            )

            if (res.isSuccessful) {
                val data = res.body()?.data!!
                val state = DetailFavouriteState(data = data[0], isLoading = false)
                emit(state)
            } else {
                val state = DetailFavouriteState(isLoading = false, error = res.message())
                emit(state)

            }

        }.flowOn(Dispatchers.IO)
    }
}
