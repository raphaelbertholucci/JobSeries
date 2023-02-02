package com.bertholucci.data.repository

import com.bertholucci.data.JobSeriesApi
import com.bertholucci.data.mapper.EpisodeMapper
import com.bertholucci.data.mapper.ShowMapper
import com.bertholucci.domain.model.Episode
import com.bertholucci.domain.model.Show
import com.bertholucci.domain.repository.ShowRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ShowRepositoryImpl(private val api: JobSeriesApi) : ShowRepository {

    override fun getShowsByQuery(query: String): Flow<List<Show>> {
        return flow {
            emit(api.getShowsByQuery(query).map {
                ShowMapper.mapToDomain(it.show)
            })
        }
    }

    override fun getShowById(id: Int): Flow<Show> {
        return flow {
            emit(ShowMapper.mapToDomain(api.getShowById(id = id)))
        }
    }

    override fun getEpisodeById(id: Int): Flow<Episode> {
        return flow {
            emit(EpisodeMapper.mapToDomain(api.getEpisodeById(id)))
        }
    }
}