package com.bertholucci.data.repository

import com.bertholucci.data.JobSeriesApi
import com.bertholucci.data.mapper.EpisodeMapper
import com.bertholucci.data.mapper.ShowMapper
import com.bertholucci.domain.model.Episode
import com.bertholucci.domain.model.Show
import com.bertholucci.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeRepositoryImpl(private val api: JobSeriesApi) : HomeRepository {

    override fun getShows(page: Int): Flow<List<Show>> {
        return flow {
            emit(ShowMapper.mapToDomainList(api.getShows(page)))
        }
    }

    override fun getShowById(id: Int): Flow<Show> {
        return flow {
            emit(ShowMapper.mapToDomain(api.getShowsById(id)))
        }
    }

    override fun getEpisodeById(id: Int): Flow<Episode> {
        return flow {
            emit(EpisodeMapper.mapToDomain(api.getEpisodesById(id)))
        }
    }
}