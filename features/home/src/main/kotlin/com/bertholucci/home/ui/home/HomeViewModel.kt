package com.bertholucci.home.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bertholucci.domain.helper.JobSeriesResponse
import com.bertholucci.domain.interactor.GetShows
import com.bertholucci.domain.interactor.GetShowsByQuery
import com.bertholucci.domain.model.Show
import com.bertholucci.home.extensions.response
import kotlinx.coroutines.flow.Flow

class HomeViewModel(
    private val getShowsUseCase: GetShows,
    private val getShowsByQueryUseCase: GetShowsByQuery
) : ViewModel() {

    private val _shows = MutableLiveData<JobSeriesResponse<List<Show>>>()
    val shows: LiveData<JobSeriesResponse<List<Show>>>
        get() = _shows

    init {
        getShows()
    }

    fun getShows(page: Int = 0) {
        getShowsUseCase(requestValues = page).observe()
    }

    fun getShowsByQuery(query: String) {
        getShowsByQueryUseCase(requestValues = query).observe()
    }

    private fun Flow<List<Show>>.observe() {
        this.response(_shows, viewModelScope)
    }
}