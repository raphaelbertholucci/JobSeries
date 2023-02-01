package com.bertholucci.domain.interactor

import com.bertholucci.domain.UseCase
import com.bertholucci.domain.model.Show
import com.bertholucci.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

class GetShowsByQuery(private val repository: HomeRepository) :
    UseCase<String, List<Show>>() {

    override fun executeUseCase(requestValues: String): Flow<List<Show>> {
        return repository.getShowsByQuery(query = requestValues)
    }
}