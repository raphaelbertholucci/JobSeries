package com.bertholucci.domain.repository

import com.bertholucci.domain.model.Episode
import com.bertholucci.domain.model.Show
import kotlinx.coroutines.flow.Flow

interface ShowRepository {
    fun getShowsByQuery(query: String): Flow<List<Show>>
    fun getShowById(id: Int): Flow<Show>
    fun getEpisodeById(id: Int): Flow<Episode>
}