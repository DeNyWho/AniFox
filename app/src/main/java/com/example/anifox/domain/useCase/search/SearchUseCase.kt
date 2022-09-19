package com.example.anifox.domain.useCase.search

import com.example.anifox.data.repository.MangaRepository
import com.example.anifox.domain.model.manga.toData
import com.example.anifox.presentation.search.state.search.SearcherState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val repository: MangaRepository
) {
    operator fun invoke(query: String): Flow<SearcherState> {
        return flow {
            emit(SearcherState(isLoading = true))
            val res = repository.getSearch(query)
            println("RES = ${res.body()?.message}")
            if (res.isSuccessful){
                val data = res.body()?.data?.map { it.toData() }.orEmpty()
                if(data.isEmpty()){
                    println("RES = ${res.body()?.message}")
                    val state = SearcherState(null, isLoading = false, message = res.body()?.message)
                    emit(state)
                } else {
                    val state = SearcherState(data, isLoading = false)
                    emit(state)
                }
            } else {
                val state = SearcherState(isLoading = false, error = res.message())
                emit(state)
            }

        }.flowOn(Dispatchers.IO)
    }
}