package com.bertholucci.domain.interactor

import com.bertholucci.domain.UseCase
import com.bertholucci.domain.model.Show
import com.bertholucci.domain.repository.ShowRepository
import kotlinx.coroutines.flow.Flow

class GetShowById(private val repository: ShowRepository) :
    UseCase<Int, Show>() {

    override fun executeUseCase(requestValues: Int): Flow<Show> {
        return repository.getShowById(id = requestValues)
    }
}