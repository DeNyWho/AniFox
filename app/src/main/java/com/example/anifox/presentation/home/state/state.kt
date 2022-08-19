package com.example.anifox.presentation.home.state

import com.example.anifox.presentation.home.state.announces.AnnouncesState
import com.example.anifox.presentation.home.state.manga.magic.MagicState
import com.example.anifox.presentation.home.state.manga.middleAges.MiddleAgesState
import com.example.anifox.presentation.home.state.manga.monsters.MonstersState
import com.example.anifox.presentation.home.state.mostRead.MostReadState
import com.example.anifox.presentation.home.state.popular.AiringPopularAnimeState
import com.example.anifox.presentation.home.state.popular.PopularCompletedState
import com.example.anifox.presentation.home.state.popular.PopularMangaState

data class HomeState (
    val airingPopularState: AiringPopularAnimeState,
    val popularCompleted: PopularCompletedState,
    val middleAgesState: MiddleAgesState,
    val popularState: PopularMangaState,
    val announcesState: AnnouncesState,
    val mostRead: MostReadState,
    val monsters: MonstersState,
    val magic: MagicState,
)