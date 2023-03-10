package com.bertholucci.data.mapper.response

import com.bertholucci.data.mapper.BaseMapper
import com.bertholucci.data.model.EpisodeResponse
import com.bertholucci.domain.model.Episode

object EpisodeMapper : BaseMapper<EpisodeResponse, Episode> {
    override fun mapFromDomain(domain: Episode): EpisodeResponse {
        throw UnsupportedOperationException("Operation not supported!")
    }

    override fun mapToDomain(response: EpisodeResponse): Episode {
        return Episode(
            id = response.id,
            name = response.name ?: "",
            number = response.number ?: 0,
            season = response.season ?: 0,
            runtime = response.runtime ?: "",
            airDate = response.airDate ?: "",
            airTime = response.airTime ?: "",
            rating = RatingMapper.mapToDomain(response.rating),
            summary = response.summary ?: "",
            image = ImageMapper.mapToDomain(response.image)
        )
    }
}