package com.bertholucci.home.di

import com.bertholucci.home.details.ShowDetailsViewModel
import com.bertholucci.home.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(getShowsUseCase = get(), getShowsByQueryUseCase = get()) }
    viewModel { (showId: Int) -> ShowDetailsViewModel(showId = showId, useCase = get()) }
}