package com.bertholucci.home.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bertholucci.domain.helper.JobSeriesResponse
import com.bertholucci.domain.interactor.GetShowById
import com.bertholucci.domain.model.Show
import com.bertholucci.home.extensions.failure
import com.bertholucci.home.extensions.hideLoading
import com.bertholucci.home.extensions.showLoading
import com.bertholucci.home.extensions.success
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

class ShowDetailsViewModel(showId: Int, private val useCase: GetShowById) : ViewModel() {

    private val _show = MutableLiveData<JobSeriesResponse<Show>>()
    val show: LiveData<JobSeriesResponse<Show>>
        get() = _show

    init {
        getShowById(showId = showId)
    }

    private fun getShowById(showId: Int) {
        useCase(showId)
            .onStart { _show.showLoading() }
            .onCompletion { _show.hideLoading() }
            .map { _show.success(it) }
            .catch { _show.failure(it) }
            .launchIn(viewModelScope)
    }
}