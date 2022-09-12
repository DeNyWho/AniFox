package com.example.anifox.domain.useCase.login

import com.example.anifox.data.repository.AuthRepository
import com.example.anifox.data.repository.DataStoreRepository
import com.example.anifox.domain.model.user.UserSignUp
import com.example.anifox.domain.model.user.toData
import com.example.anifox.presentation.signUp.state.signUp.UserSignUpState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val repository: AuthRepository,
    private val dataStoreOperations: DataStoreRepository
) {
    operator fun invoke(username: String, password: String, email: String): Flow<UserSignUpState> {
        return flow {
            emit(UserSignUpState(isLoading = true))
            val res = repository.signUp(
                UserSignUp(
                    username,
                    email,
                    password
                )
            )

            if (res.isSuccessful){
                val data = res.body()?.data?.map { it.toData() }.orEmpty()
                if(data.isNotEmpty()) {
                    dataStoreOperations.updateSession(
                        token = data[0].token,
                        data[0].username,
                        data[0].email
                    )

                    val state = UserSignUpState(data, isLoading = false)
                    emit(state)
                } else {
                    val state = UserSignUpState(null, isLoading = false, message = res.body()?.message)
                    emit(state)
                }
            } else {
                val state = UserSignUpState(isLoading = false, error = res.message())
                emit(state)

            }

        }.flowOn(Dispatchers.IO)
    }
}

