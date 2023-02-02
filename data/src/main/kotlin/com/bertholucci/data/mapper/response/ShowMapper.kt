package com.bertholucci.data.mapper.response

import com.bertholucci.data.mapper.BaseMapper
import com.bertholucci.data.model.EpisodeResponse
import com.bertholucci.data.model.ShowResponse
import com.bertholucci.domain.model.Episode
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
            episodes = episodesMapper(response.embedded?.episodes)
        )
    }

    fun mapToDomainList(responseList: List<ShowResponse>) = responseList.map { response ->
        mapToDomain(response)
    }

    private fun episodesMapper(episodesResponse: List<EpisodeResponse>?): List<Episode> {
        val episodes = mutableListOf<Episode>()
        var previousSeason = 0

        episodesResponse?.forEach { episode ->
            if (episode.season == previousSeason) {
                episodes.add(EpisodeMapper.mapToDomain(episode))
            } else {
                episodes.add(Episode(season = episode.season ?: 0))
                episodes.add(EpisodeMapper.mapToDomain(episode))
                previousSeason = episode.season ?: 0
            }
        }
        return episodes
    }
}