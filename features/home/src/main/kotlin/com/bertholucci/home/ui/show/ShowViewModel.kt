package com.bertholucci.home.ui.show

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bertholucci.domain.helper.JobSeriesResponse
import com.bertholucci.domain.interactor.GetShowById
import com.bertholucci.domain.interactor.GetShowByIdFromDB
import com.bertholucci.domain.interactor.InsertEpisodeIntoDB
import com.bertholucci.domain.interactor.InsertShowIntoDB
import com.bertholucci.domain.interactor.RemoveEpisodeFromDB
import com.bertholucci.domain.interactor.RemoveShowIntoDB
import com.bertholucci.domain.model.Show
import com.bertholucci.home.extensions.episodesListFiltered
import com.bertholucci.home.extensions.failure
import com.bertholucci.home.extensions.hideLoading
import com.bertholucci.home.extensions.showLoading
import com.bertholucci.home.extensions.success
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

class ShowViewModel(
    showId: Int,
    fromFavorites: Boolean,
    private val useCase: GetShowById,
    private val insertShowIntoDB: InsertShowIntoDB,
    private val insertEpisodeIntoDB: InsertEpisodeIntoDB,
    private val removeShowIntoDB: RemoveShowIntoDB,
    private val removeEpisodeFromDB: RemoveEpisodeFromDB,
    private val getShowByIdFromDB: GetShowByIdFromDB
) : ViewModel() {

    private val id = showId
    val isFromFavorites = fromFavorites

    val isFavorite = MutableLiveData<Boolean>()
    val hasChanged = MutableLiveData(false)
    val show = MutableLiveData<Show>()

    private val _showResponse = MutableLiveData<JobSeriesResponse<Show>>()
    val showResponse: LiveData<JobSeriesResponse<Show>>
        get() = _showResponse

    init {
        getShowFromDataSource()
    }

    fun getShowFromDataSource() {
        when {
            isFromFavorites -> getShowFromDB(id)
            else -> getShowById(showId = id)
        }
    }

    fun updateShowState() {
        show.value?.let { show ->
            if (isFavorite.value == true) {
                removeShow(show)
                removeEpisodesFromDB(show)
            } else {
                insertShow(show)
                insertEpisodesIntoDB(show)
            }
            hasChanged.postValue(true)
        }
    }

    private fun getShowById(showId: Int = id) {
        useCase(showId)
            .onStart { _showResponse.showLoading() }
            .onCompletion { _showResponse.hideLoading() }
            .map {
                _showResponse.success(it)
                getShowFromDB(it.id)
            }.catch { _showResponse.failure(it) }
            .launchIn(viewModelScope)
    }

    private fun insertShow(show: Show) {
        insertShowIntoDB(show)
            .map { isFavorite.postValue(true) }
            .catch { isFavorite.postValue(false) }
            .launchIn(viewModelScope)
    }

    private fun removeShow(show: Show) {
        removeShowIntoDB(show)
            .map { isFavorite.postValue(false) }
            .catch { isFavorite.postValue(true) }
            .launchIn(viewModelScope)
    }

    private fun getShowFromDB(id: Int) {
        getShowByIdFromDB(id)
            .map {
                if (isFromFavorites) _showResponse.success(it)
                isFavorite.postValue(true)
            }.catch {
                if (isFromFavorites) _showResponse.failure(it)
                isFavorite.postValue(false)
            }.launchIn(viewModelScope)
    }

    private fun insertEpisodesIntoDB(show: Show) {
        show.episodesListFiltered().forEach { insertEpisodeIntoDB(it).launchIn(viewModelScope) }
    }

    private fun removeEpisodesFromDB(show: Show) {
        show.episodesListFiltered().forEach { removeEpisodeFromDB(it).launchIn(viewModelScope) }
    }

    fun updateShow(s: Show) {
        show.value = s
    }
}
