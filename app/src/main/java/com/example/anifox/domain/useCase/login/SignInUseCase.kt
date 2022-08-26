package com.example.anifox.domain.useCase.login

import com.example.anifox.data.repository.DataStoreRepository
import com.example.anifox.data.repository.UserRepository
import com.example.anifox.domain.model.user.UserSignIn
import com.example.anifox.domain.model.user.toData
import com.example.anifox.presentation.login.state.UserSignInState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val repository: UserRepository,
    private val dataStoreOperations: DataStoreRepository
) {
    operator fun invoke(email: String, password: String): Flow<UserSignInState> {
        return flow {
            emit(UserSignInState(isLoading = true))
            val res = repository.signIn(
                UserSignIn(
                    email,
                    password
                )
            )

            if (res.isSuccessful){
                val data = res.body()?.data?.map { it.toData() }.orEmpty()
                Timber.d("DATA = $data")
                dataStoreOperations.updateSession(token = data[0].token, data[0].username, data[0].email)

                val state = UserSignInState(data, isLoading = false)
                emit(state)
            } else {
                val state = UserSignInState(isLoading = false, error = res.message())
                emit(state)

            }

        }.flowOn(Dispatchers.IO)
    }
}
