package com.bertholucci.home.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bertholucci.domain.helper.JobSeriesResponse
import com.bertholucci.domain.interactor.GetShows
import com.bertholucci.domain.interactor.GetShowsByQuery
import com.bertholucci.domain.model.Show
import com.bertholucci.home.extensions.failure
import com.bertholucci.home.extensions.hideLoading
import com.bertholucci.home.extensions.showLoading
import com.bertholucci.home.extensions.success
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

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
        this.onStart { _shows.showLoading() }
            .onCompletion { _shows.hideLoading() }
            .map { _shows.success(it) }
            .catch { _shows.failure(it) }
            .launchIn(viewModelScope)
    }
}