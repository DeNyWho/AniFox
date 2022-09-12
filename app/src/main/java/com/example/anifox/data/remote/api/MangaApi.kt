package com.example.anifox.data.remote.api

import com.example.anifox.domain.model.manga.NewFavouriteManga
import com.example.anifox.domain.model.responses.BasicBooleanResponse
import com.example.anifox.domain.model.responses.MangaResponse
import com.example.anifox.domain.model.responses.PagingFavouriteResponse
import retrofit2.Response
import retrofit2.http.*

interface MangaApi {

    @GET("manga/")
    suspend fun getManga(
        @Query("pageNum") page: Int,
        @Query("pageSize") countCard: Int,
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

    @POST("user/favourite/add")
    suspend fun newFavourite(
        @Query("status") status: String,
        @Body newFavourite: NewFavouriteManga
    ): Response<BasicBooleanResponse>

}