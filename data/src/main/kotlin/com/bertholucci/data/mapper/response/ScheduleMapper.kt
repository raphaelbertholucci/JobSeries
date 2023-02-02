package com.bertholucci.data.mapper.response

import com.bertholucci.data.mapper.BaseMapper
import com.bertholucci.data.model.ScheduleResponse
import com.bertholucci.domain.model.Schedule

object ScheduleMapper: BaseMapper<ScheduleResponse?, Schedule> {
    override fun mapFromDomain(domain: Schedule): ScheduleResponse {
        throw UnsupportedOperationException("Operation not supported!")
    }

    override fun mapToDomain(response: ScheduleResponse?): Schedule {
        return Schedule(
            time = response?.time ?: "",
            days = response?.days ?: listOf()
        )
    }
}