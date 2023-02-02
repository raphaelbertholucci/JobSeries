package com.bertholucci.data.mapper.entity

import com.bertholucci.data.mapper.BaseMapper
import com.bertholucci.data.model.RatingEntity
import com.bertholucci.domain.model.Rating

object RatingEntityMapper : BaseMapper<RatingEntity?, Rating> {
    override fun mapFromDomain(domain: Rating): RatingEntity {
        return RatingEntity(
            average = domain.average
        )
    }

    override fun mapToDomain(response: RatingEntity?): Rating {
        return Rating(
            average = response?.average ?: ""
        )
    }
}