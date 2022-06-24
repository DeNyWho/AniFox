package com.example.anifox.presentation.login

//import com.example.anifox.domain.useCase.login.GetTokenUseCase
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginFragmentViewModel @Inject constructor(
//    private val getTokenUseCase: GetTokenUseCase,
): ViewModel() {
//    private val _tokenState : MutableSharedFlow<AuthState> = MutableSharedFlow()
//    val tokenState = _tokenState.asSharedFlow()
//
//
//    suspend fun getTokens(authCode: String){
//        getTokenUseCase.invoke(authCode).onEach { value ->
//            _tokenState.tryEmit(value)
//        }.launchIn(viewModelScope)
//    }


}