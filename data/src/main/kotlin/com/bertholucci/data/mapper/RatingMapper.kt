package com.bertholucci.data.mapper

import com.bertholucci.data.model.RatingResponse
import com.bertholucci.domain.model.Rating

object RatingMapper : BaseMapper<RatingResponse?, Rating> {
    override fun mapFromDomain(domain: Rating): RatingResponse {
        throw UnsupportedOperationException("Operation not supported!")
    }

    override fun mapToDomain(response: RatingResponse?): Rating {
        return Rating(
            average = response?.average ?: ""
        )
    }
}