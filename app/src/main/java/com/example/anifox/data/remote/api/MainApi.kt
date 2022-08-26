package com.example.anifox.data.remote.api

import com.example.anifox.domain.model.responses.MangaResponse
import com.example.anifox.domain.model.responses.PagingFavouriteResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MainApi {

    @GET("manga/")
    suspend fun getManga(
        @Query("page") page: Int,
        @Query("countCard") countCard: Int,
        @Query("status") status: String?,
        @Query("order") order: String?,
        @Query("genre") genre: String?
    ): Response<MangaResponse>

    @GET("manga/{id}")
    suspend fun getDetailManga(
        @Path("id") id: Int,
    ): Response<MangaResponse>

    @GET("user/favourite/")
    suspend fun getMangaByUser(
        @Query("token") token: String,
        @Query("pageNum") pageNum: Int,
        @Query("pageSize") pageSize: Int,
        @Query("status") status: String?,
    ): Response<PagingFavouriteResponse>

    @GET("manga/search")
    suspend fun getSearchManga(
        @Query("query") query: String,
    ): Response<MangaResponse>

}