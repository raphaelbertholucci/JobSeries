package com.bertholucci.home.di

import com.bertholucci.home.ui.episode.EpisodeViewModel
import com.bertholucci.home.ui.favorites.FavoritesViewModel
import com.bertholucci.home.ui.home.HomeViewModel
import com.bertholucci.home.ui.search.SearchViewModel
import com.bertholucci.home.ui.show.ShowViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(getShowsUseCase = get()) }
    viewModel { SearchViewModel(getShowsByQueryUseCase = get()) }
    viewModel { (showId: Int, fromFavorites: Boolean) ->
        ShowViewModel(
            showId = showId,
            fromFavorites = fromFavorites,
            useCase = get(),
            insertShowIntoDB = get(),
            insertEpisodeIntoDB = get(),
            removeShowIntoDB = get(),
            removeEpisodeFromDB = get(),
            getShowByIdFromDB = get()
        )
    }
    viewModel { (episodeId: Int, fromFavorites: Boolean) ->
        EpisodeViewModel(
            episodeId = episodeId,
            fromFavorites = fromFavorites,
            getEpisodeById = get(),
            getEpisodeByIdFromDB = get()
        )
    }
    viewModel { FavoritesViewModel(getShowsFromDB = get()) }
}