package com.bertholucci.domain.interactor

import androidx.paging.PagingData
import com.bertholucci.domain.UseCase
import com.bertholucci.domain.model.Show
import com.bertholucci.domain.repository.ShowRepository
import kotlinx.coroutines.flow.Flow

class GetShows(private val repository: ShowRepository) :
    UseCase<Unit, PagingData<Show>>() {

    override fun executeUseCase(requestValues: Unit): Flow<PagingData<Show>> {
        return repository.getShows()
    }
}