package com.bertholucci.home.ui.episode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bertholucci.domain.helper.JobSeriesResponse
import com.bertholucci.domain.interactor.GetEpisodeById
import com.bertholucci.domain.interactor.GetEpisodeByIdFromDB
import com.bertholucci.domain.model.Episode
import com.bertholucci.home.extensions.response

class EpisodeViewModel(
    episodeId: Int,
    fromFavorites: Boolean,
    private val getEpisodeById: GetEpisodeById,
    private val getEpisodeByIdFromDB: GetEpisodeByIdFromDB
) : ViewModel() {

    private val id = episodeId

    private val _episode = MutableLiveData<JobSeriesResponse<Episode>>()
    val episode: LiveData<JobSeriesResponse<Episode>>
        get() = _episode

    init {
        getEpisodeFromDataSource(episodeId, fromFavorites)
    }

    private fun getEpisodeFromDataSource(id: Int, fromFavorites: Boolean) {
        when {
            fromFavorites -> getEpisodeFromDB(episodeId = id)
            else -> getEpisode(episodeId = id)
        }
    }

    fun getEpisode(episodeId: Int = id) {
        getEpisodeById(requestValues = episodeId).response(_episode, viewModelScope)
    }

    fun getEpisodeFromDB(episodeId: Int = id) {
        getEpisodeByIdFromDB(requestValues = episodeId).response(_episode, viewModelScope)
    }
}