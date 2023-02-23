package com.bertholucci.domain.interactor

import com.bertholucci.domain.UseCase
import com.bertholucci.domain.model.Episode
import com.bertholucci.domain.repository.ShowRepository
import kotlinx.coroutines.flow.Flow

class InsertEpisodeIntoDB(private val repository: ShowRepository) :
    UseCase<Episode, Unit>() {

    override fun executeUseCase(requestValues: Episode): Flow<Unit> {
        return repository.insertEpisode(requestValues)
    }
}