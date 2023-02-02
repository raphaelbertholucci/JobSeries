package com.bertholucci.domain.interactor

import com.bertholucci.domain.UseCase
import com.bertholucci.domain.model.Show
import com.bertholucci.domain.repository.ShowRepository
import kotlinx.coroutines.flow.Flow

class GetShowsFromDB(private val repository: ShowRepository) :
    UseCase<Unit, List<Show>>() {

    override fun executeUseCase(requestValues: Unit): Flow<List<Show>> {
        return repository.getShowsFromDB()
    }
}