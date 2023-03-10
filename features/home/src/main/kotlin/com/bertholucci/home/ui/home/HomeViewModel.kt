package com.bertholucci.home.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.bertholucci.domain.interactor.GetShows

class HomeViewModel(
    getShowsUseCase: GetShows
) : ViewModel() {

    val shows = getShowsUseCase(Unit).cachedIn(viewModelScope)
}
