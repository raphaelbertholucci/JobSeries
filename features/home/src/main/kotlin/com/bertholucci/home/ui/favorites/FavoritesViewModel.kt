package com.bertholucci.home.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bertholucci.domain.helper.JobSeriesResponse
import com.bertholucci.domain.interactor.GetShowsFromDB
import com.bertholucci.domain.model.Show
import com.bertholucci.home.extensions.failure
import com.bertholucci.home.extensions.success
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map

class FavoritesViewModel(
    private val getShowsFromDB: GetShowsFromDB
) : ViewModel() {

    private val _shows = MutableLiveData<JobSeriesResponse<List<Show>>>()
    val shows: LiveData<JobSeriesResponse<List<Show>>>
        get() = _shows

    init {
        getShows()
    }

    fun getShows() {
        getShowsFromDB(Unit)
            .map { _shows.success(it) }
            .catch { _shows.failure(it) }
            .launchIn(viewModelScope)
    }
}