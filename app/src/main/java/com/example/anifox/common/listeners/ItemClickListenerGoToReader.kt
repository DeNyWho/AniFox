package com.example.anifox.common.listeners

import com.example.anifox.domain.model.manga.Manga

interface ItemClickListenerGoToReader {
    fun navigationToReader(url: String, manga: Manga)
}