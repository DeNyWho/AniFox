package com.example.anifox.data.remote.api

import com.example.anifox.domain.model.anime.Anime
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AnimeApi {
    @GET("animes")
    suspend fun getAnimes(
        @Query("page") page: Int?,
        @Query("limit") limit: Int?,
    ): List<Anime>

    @GET("animes")
    suspend fun getAnimesPopular(
        @Query("page") page: Int?,
        @Query("limit") limit: Int?,
        @Query("order") order: String?,
    ): List<Anime>

    @GET("animes")
    suspend fun getAnimesPreview(
        @Query("page") page: Int?,
        @Query("limit") limit: Int?,
        @Query("order") order: String?,
    ): Response<List<Anime>>



}