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
            removeShowIntoDB = get(),
            getShowByIdFromDB = get()
        )
    }
    viewModel { (episodeId: Int) ->
        EpisodeViewModel(
            episodeId = episodeId,
            getEpisodeById = get()
        )
    }
    viewModel { FavoritesViewModel(getShowsFromDB = get()) }
}