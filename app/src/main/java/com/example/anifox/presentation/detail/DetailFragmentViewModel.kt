package com.example.anifox.presentation.detail

//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.anifox.domain.useCase.detail.GetDetailsUseCase
//import com.example.anifox.presentation.detail.state.ContentDetailsState
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.asStateFlow
//import kotlinx.coroutines.flow.launchIn
//import kotlinx.coroutines.flow.onEach
//import timber.log.Timber
//import javax.inject.Inject
//
//@HiltViewModel
//class DetailFragmentViewModel @Inject constructor(
////    private val getDetails: GetDetailsUseCase
//) : ViewModel() {
//    private val _animeDetails = MutableStateFlow(ContentDetailsState())
//    val animeDetails = _animeDetails.asStateFlow()
//
//    private val _queries = MutableStateFlow(0)
//
//    fun setQueries(malId: Int) {
//        _queries.tryEmit(malId)
//    }
//
//    fun getDetails(){
//        Timber.d("QUERIES = ${_queries.value}")
//        getDetails.invoke(_queries.value).onEach {
//                value -> _animeDetails.tryEmit(value)
//        } .launchIn(viewModelScope)
//    }
//
//}