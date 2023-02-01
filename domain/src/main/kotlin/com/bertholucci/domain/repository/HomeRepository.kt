package com.bertholucci.domain.repository

import androidx.paging.PagingData
import com.bertholucci.domain.model.Show
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getShows(): Flow<PagingData<Show>>
}