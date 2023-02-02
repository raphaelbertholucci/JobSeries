package com.bertholucci.data.mapper.entity

import com.bertholucci.data.extensions.fromJson
import com.bertholucci.data.mapper.BaseMapper
import com.bertholucci.data.model.EpisodeEntity
import com.bertholucci.data.model.ShowEntity
import com.bertholucci.domain.model.Episode
import com.bertholucci.domain.model.Show
import com.google.gson.Gson

object ShowEntityMapper : BaseMapper<ShowEntity, Show> {

    private val gson = Gson()

    override fun mapFromDomain(domain: Show): ShowEntity {
        return ShowEntity(
            id = domain.id,
            name = domain.name,
            language = domain.language,
            genres = gson.toJson(domain.genres),
            averageRuntime = domain.averageRuntime,
            premiered = domain.premiered,
            ended = domain.ended,
            status = domain.status,
            schedule = gson.toJson(ScheduleEntityMapper.mapFromDomain(domain.schedule)),
            rating = gson.toJson(RatingEntityMapper.mapFromDomain(domain.rating)),
            summary = domain.summary,
            image = gson.toJson(ImageEntityMapper.mapFromDomain(domain.image)),
            episodes = gson.toJson(EpisodeEntityMapper.mapFromDomainList(domain.episodes))
        )
    }

    override fun mapToDomain(response: ShowEntity): Show {
        return Show(
            id = response.id,
            name = response.name,
            language = response.language,
            genres = gson.fromJson(response.genres),
            averageRuntime = response.averageRuntime,
            premiered = response.premiered,
            ended = response.ended,
            status = response.status,
            schedule = ScheduleEntityMapper.mapToDomain(gson.fromJson(response.schedule)),
            rating = RatingEntityMapper.mapToDomain(gson.fromJson(response.rating)),
            summary = response.summary,
            image = ImageEntityMapper.mapToDomain(gson.fromJson(response.image)),
            episodes = EpisodeEntityMapper.mapToDomainList(gson.fromJson(response.episodes))
        )
    }

    fun mapToDomainList(responseList: List<ShowEntity>) = responseList.map { response ->
        mapToDomain(response)
    }
}