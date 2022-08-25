package com.example.anifox.domain.useCase.search

import com.example.anifox.data.repository.MangaRepository
import com.example.anifox.domain.model.manga.toData
import com.example.anifox.presentation.search.state.search.SearcherState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val repository: MangaRepository
) {
    operator fun invoke(query: String): Flow<SearcherState> {
        return flow {
            emit(SearcherState(isLoading = true))
            val res = repository.getSearch(query)
            Timber.d("DATA = ${res.body()?.data}")
            if (res.isSuccessful){
                val data = res.body()?.data?.map { it.toData() }.orEmpty()
                val state = SearcherState(data, isLoading = false)
                emit(state)
            } else {
                val state = SearcherState(isLoading = false, error = res.message())
                emit(state)
            }

        }.flowOn(Dispatchers.IO)
    }
}