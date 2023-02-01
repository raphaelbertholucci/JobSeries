package com.bertholucci.domain.interactor

import com.bertholucci.domain.UseCase
import com.bertholucci.domain.model.Episode
import com.bertholucci.domain.repository.ShowRepository
import kotlinx.coroutines.flow.Flow

class GetEpisodeById(private val repository: ShowRepository) :
    UseCase<Int, Episode>() {

    override fun executeUseCase(requestValues: Int): Flow<Episode> {
        return repository.getEpisodeById(id = requestValues)
    }
}