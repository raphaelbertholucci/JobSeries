package com.bertholucci.domain.interactor

import com.bertholucci.domain.UseCase
import com.bertholucci.domain.model.Show
import com.bertholucci.domain.repository.ShowRepository
import kotlinx.coroutines.flow.Flow

class RemoveShowIntoDB(private val repository: ShowRepository) :
    UseCase<Show, Unit>() {

    override fun executeUseCase(requestValues: Show): Flow<Unit> {
        return repository.removeShow(requestValues)
    }
}