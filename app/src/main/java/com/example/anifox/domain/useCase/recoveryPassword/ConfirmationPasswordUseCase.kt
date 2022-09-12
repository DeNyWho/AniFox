package com.example.anifox.domain.useCase.recoveryPassword

import com.example.anifox.data.repository.AuthRepository
import com.example.anifox.domain.model.user.toData
import com.example.anifox.presentation.recoveryPassword.state.PasswordConfirmationState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ConfirmationPasswordUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke(email: String): Flow<PasswordConfirmationState> {
        return flow {
            emit(PasswordConfirmationState(isLoading = true))
            val res = repository.confirmationPassword(email)

            if (res.isSuccessful){
                val data = res.body()?.data?.map { it.toData() }.orEmpty()

                val state = PasswordConfirmationState(data, isLoading = false)
                emit(state)
            } else {
                val state = PasswordConfirmationState(isLoading = false, error = res.message())
                emit(state)

            }

        }.flowOn(Dispatchers.IO)
    }
}
