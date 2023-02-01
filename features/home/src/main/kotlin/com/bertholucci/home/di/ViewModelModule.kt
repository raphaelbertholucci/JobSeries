package com.bertholucci.home.di

import com.bertholucci.home.ui.episode.EpisodeViewModel
import com.bertholucci.home.ui.show.ShowViewModel
import com.bertholucci.home.ui.home.HomeViewModel
import com.bertholucci.home.ui.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(getShowsUseCase = get()) }
    viewModel { SearchViewModel(getShowsByQueryUseCase = get()) }
    viewModel { (showId: Int) -> ShowViewModel(showId = showId, useCase = get()) }
    viewModel { (episodeId: Int) -> EpisodeViewModel(episodeId = episodeId, getEpisodeById = get()) }
}