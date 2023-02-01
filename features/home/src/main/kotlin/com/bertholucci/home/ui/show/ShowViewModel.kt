package com.bertholucci.home.ui.show

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bertholucci.domain.helper.JobSeriesResponse
import com.bertholucci.domain.interactor.GetShowById
import com.bertholucci.domain.model.Show
import com.bertholucci.home.extensions.response

class ShowViewModel(showId: Int, private val useCase: GetShowById) : ViewModel() {

    private val id = showId

    private val _show = MutableLiveData<JobSeriesResponse<Show>>()
    val show: LiveData<JobSeriesResponse<Show>>
        get() = _show

    init {
        getShowById(showId = id)
    }

    fun getShowById(showId: Int = id) {
        useCase(showId).response(_show, viewModelScope)
    }
}