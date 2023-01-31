package com.bertholucci.domain.repository

import com.bertholucci.domain.model.Episode
import com.bertholucci.domain.model.Show
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getShows(page: Int): Flow<List<Show>>
    fun getShowById(id: Int): Flow<Show>
    fun getEpisodeById(id: Int): Flow<Episode>
}