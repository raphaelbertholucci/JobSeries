package com.bertholucci.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.bertholucci.data.JobSeriesApi
import com.bertholucci.data.database.DatabaseDao
import com.bertholucci.data.mapper.entity.EpisodeEntityMapper
import com.bertholucci.data.mapper.entity.ShowEntityMapper
import com.bertholucci.data.mapper.response.EpisodeMapper
import com.bertholucci.data.mapper.response.ShowMapper
import com.bertholucci.domain.model.Episode
import com.bertholucci.domain.model.Show
import com.bertholucci.domain.repository.ShowRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class ShowRepositoryImpl(
    private val api: JobSeriesApi,
    private val dao: DatabaseDao
) : ShowRepository {

    override fun getShows(): Flow<PagingData<Show>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                maxSize = 30,
                enablePlaceholders = true
            ),
            pagingSourceFactory = {
                HomePagingSource(api)
            }
        ).flow
    }

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

    override fun getShowsFromDB(): Flow<List<Show>> {
        return flow {
            emit(ShowEntityMapper.mapToDomainList(dao.getShows()))
        }
    }

    override fun insertShow(show: Show): Flow<Unit> {
        return flow {
            emit(dao.insertShow(ShowEntityMapper.mapFromDomain(show)))
        }
    }

    override fun insertEpisode(episode: Episode): Flow<Unit> {
        return flow {
            emit(dao.insertEpisode(EpisodeEntityMapper.mapFromDomain(episode)))
        }
    }

    override fun removeShow(show: Show): Flow<Unit> {
        return flow {
            emit(dao.removeShow(ShowEntityMapper.mapFromDomain(show)))
        }
    }

    override fun removeEpisode(episode: Episode): Flow<Unit> {
        return flow {
            emit(dao.removeEpisode(EpisodeEntityMapper.mapFromDomain(episode)))
        }
    }

    override fun getShowByIdFromDB(id: Int): Flow<Show> {
        return flow {
            emit(ShowEntityMapper.mapToDomain(dao.getShowByIDFromDB(id = id)))
        }
    }

    override fun getEpisodeByIdFromDB(id: Int): Flow<Episode> {
        return flow {
            emit(EpisodeEntityMapper.mapToDomain(dao.getEpisodeByIDFromDB(id = id)))
        }
    }
}