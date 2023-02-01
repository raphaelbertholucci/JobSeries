package com.bertholucci.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bertholucci.data.JobSeriesApi
import com.bertholucci.data.mapper.ShowMapper
import com.bertholucci.domain.model.Show

private const val STARTING_PAGE_INDEX = 0

class HomePagingSource(private val api: JobSeriesApi): PagingSource<Int, Show>() {

    override fun getRefreshKey(state: PagingState<Int, Show>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Show> {
        return try {
            val nextPage = params.key ?: STARTING_PAGE_INDEX
            val response = api.getShows(nextPage)
            val nextKey = if (response.isEmpty()) null else nextPage + 1
            LoadResult.Page(
                data = ShowMapper.mapToDomainList(response),
                nextKey = nextKey,
                prevKey = null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}