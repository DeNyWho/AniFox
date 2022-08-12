package com.example.anifox.domain.model.common

data class GenresCard(
    val title: String,
    val image: Int,
    val color: Int,
    val imageWidth: Int = 80,
    val imageHeight: Int = 80,
)