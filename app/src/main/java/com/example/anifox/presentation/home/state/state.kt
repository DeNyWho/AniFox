package com.example.anifox.presentation.home.state

import com.example.anifox.presentation.home.state.announces.AnnouncesState
import com.example.anifox.presentation.home.state.discover.DiscoverState
import com.example.anifox.presentation.home.state.mostRead.MostReadState
import com.example.anifox.presentation.home.state.popular.AiringPopularAnimeState
import com.example.anifox.presentation.home.state.popular.PopularMangaState

data class HomeState (
    val airingPopularState: AiringPopularAnimeState,
    val popularState: PopularMangaState,
    val announcesState: AnnouncesState,
    val discoverState: DiscoverState,
    val mostRead: MostReadState
)