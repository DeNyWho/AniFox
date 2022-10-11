package com.example.anifox.domain.useCase.login

import com.example.anifox.data.repository.AuthRepository
import com.example.anifox.presentation.signUp.state.exist.NameExistsState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SignUpFindByNameUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke(name: String): Flow<NameExistsState> {
        return flow {
            emit(NameExistsState(isLoading = true))
            val res = repository.authorizedFindByUsername(name)
            if (res.isSuccessful){
                val data = res.body()?.data

                val state = NameExistsState(data, isLoading = false, message = res.body()?.message)
                emit(state)
            } else {
                val state = NameExistsState(isLoading = false, error = res.message())
                emit(state)

            }

        }.flowOn(Dispatchers.IO)
    }
}
