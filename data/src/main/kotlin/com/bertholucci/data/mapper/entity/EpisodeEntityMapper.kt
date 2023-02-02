package com.bertholucci.data.mapper.entity

import com.bertholucci.data.mapper.BaseMapper
import com.bertholucci.data.model.EpisodeEntity
import com.bertholucci.domain.model.Episode

object EpisodeEntityMapper : BaseMapper<EpisodeEntity, Episode> {
    override fun mapFromDomain(domain: Episode): EpisodeEntity {
        return EpisodeEntity(
            id = domain.id,
            name = domain.name,
            number = domain.number,
            season = domain.season,
            runtime = domain.runtime,
            airDate = domain.airDate,
            airTime = domain.airTime,
            rating = RatingEntityMapper.mapFromDomain(domain.rating),
            summary = domain.summary,
            image = ImageEntityMapper.mapFromDomain(domain.image)
        )
    }

    override fun mapToDomain(response: EpisodeEntity): Episode {
        return Episode(
            id = response.id,
            name = response.name,
            number = response.number,
            season = response.season,
            runtime = response.runtime,
            airDate = response.airDate,
            airTime = response.airTime,
            rating = RatingEntityMapper.mapToDomain(response.rating),
            summary = response.summary,
            image = ImageEntityMapper.mapToDomain(response.image)
        )
    }

    fun mapToDomainList(list: List<EpisodeEntity>) = list.map {
        mapToDomain(it)
    }

    fun mapFromDomainList(list: List<Episode>) = list.map {
        mapFromDomain(it)
    }
}