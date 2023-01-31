package com.bertholucci.data.mapper

import com.bertholucci.data.model.ShowResponse
import com.bertholucci.domain.model.EpisodesEmbedded
import com.bertholucci.domain.model.Show

object ShowMapper : BaseMapper<ShowResponse, Show> {
    override fun mapFromDomain(domain: Show): ShowResponse {
        throw UnsupportedOperationException("Operation not accepted!")
    }

    override fun mapToDomain(response: ShowResponse): Show {
        return Show(
            id = response.id,
            name = response.name ?: "",
            language = response.language ?: "",
            genres = response.genres,
            averageRuntime = response.averageRuntime ?: "",
            premiered = response.premiered ?: "",
            ended = response.ended ?: "",
            status = response.status ?: "",
            schedule = ScheduleMapper.mapToDomain(response.schedule),
            rating = RatingMapper.mapToDomain(response.rating),
            summary = response.summary ?: "",
            image = ImageMapper.mapToDomain(response.image),
            embedded = EpisodesEmbedded(episodes = response.embedded?.episodes?.map { episode ->
                EpisodeMapper.mapToDomain(episode)
            } ?: listOf())
        )
    }

    fun mapToDomainList(responseList: List<ShowResponse>) = responseList.map { response ->
        mapToDomain(response)
    }
}