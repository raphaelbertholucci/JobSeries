package com.bertholucci.data.mapper.entity

import com.bertholucci.data.mapper.BaseMapper
import com.bertholucci.data.model.ScheduleEntity
import com.bertholucci.domain.model.Schedule

object ScheduleEntityMapper : BaseMapper<ScheduleEntity?, Schedule> {
    override fun mapFromDomain(domain: Schedule): ScheduleEntity {
        return ScheduleEntity(
            time = domain.time,
            days = domain.days
        )
    }

    override fun mapToDomain(response: ScheduleEntity?): Schedule {
        return Schedule(
            time = response?.time ?: "",
            days = response?.days ?: listOf()
        )
    }
}