package com.bertholucci.domain.repository

import androidx.paging.PagingData
import com.bertholucci.domain.model.Episode
import com.bertholucci.domain.model.Show
import kotlinx.coroutines.flow.Flow

interface ShowRepository {
    fun getShows(): Flow<PagingData<Show>>
    fun getShowsByQuery(query: String): Flow<List<Show>>
    fun getShowById(id: Int): Flow<Show>
    fun getEpisodeById(id: Int): Flow<Episode>

    fun getShowsFromDB(): Flow<List<Show>>
    fun insertShow(show: Show): Flow<Unit>
    fun removeShow(show: Show): Flow<Unit>
    fun getShowByIdFromDB(id: Int): Flow<Show>
}