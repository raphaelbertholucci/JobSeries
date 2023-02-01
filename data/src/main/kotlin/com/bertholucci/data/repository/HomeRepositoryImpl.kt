package com.bertholucci.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.bertholucci.data.JobSeriesApi
import com.bertholucci.domain.model.Show
import com.bertholucci.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

class HomeRepositoryImpl(private val api: JobSeriesApi) : HomeRepository {

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
}