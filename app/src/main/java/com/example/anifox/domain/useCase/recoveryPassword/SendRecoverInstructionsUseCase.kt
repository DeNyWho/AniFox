package com.example.anifox.domain.useCase.recoveryPassword

import com.example.anifox.data.repository.AuthRepository
import com.example.anifox.presentation.recoveryPassword.state.SendInstructionsState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SendRecoverInstructionsUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke(email: String): Flow<SendInstructionsState> {
        return flow {
            val res = repository.sendRecoverInstructions(email)

            emit(SendInstructionsState(message = res.body()!!.message))
        }.flowOn(Dispatchers.IO)
    }
}