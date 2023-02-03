package com.bertholucci.home.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bertholucci.domain.helper.JobSeriesResponse
import com.bertholucci.domain.interactor.GetShowsByQuery
import com.bertholucci.domain.model.Show
import com.bertholucci.home.extensions.response

class SearchViewModel(private val getShowsByQueryUseCase: GetShowsByQuery) : ViewModel() {

    private val _showsFromQuery = MutableLiveData<JobSeriesResponse<List<Show>>>()
    val showsFromQuery: LiveData<JobSeriesResponse<List<Show>>>
        get() = _showsFromQuery

    fun getShowsByQuery(query: String) {
        getShowsByQueryUseCase(requestValues = query).response(_showsFromQuery, viewModelScope)
    }
}
