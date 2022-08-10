package com.example.anifox.domain.useCase.detail

//class GetDetailsUseCase @Inject constructor(
//    private val repository: MangaRepository,
//) {
//    operator fun invoke(malId: Int): Flow<ContentDetailsState> {
//        return flow {
//            emit(ContentDetailsState(isLoading = true))
//            val res = repository.getMangaByIdRu(malId)
//
//            if (res.isSuccessful){
//                val data = res.body()?.data?.toContentDetails()
//                val state = ContentDetailsState(data, isLoading = false)
//                emit(state)
//            } else {
//                val state = ContentDetailsState(isLoading = false, error = res.message())
//                emit(state)
//
//            }
//
//        }.flowOn(Dispatchers.IO)
//    }
//}