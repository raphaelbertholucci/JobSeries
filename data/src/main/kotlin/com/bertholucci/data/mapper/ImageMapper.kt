package com.bertholucci.data.mapper

import com.bertholucci.data.model.ImageResponse
import com.bertholucci.domain.model.Image

object ImageMapper : BaseMapper<ImageResponse?, Image> {
    override fun mapFromDomain(domain: Image): ImageResponse {
        throw UnsupportedOperationException("Operation not supported!")
    }

    override fun mapToDomain(response: ImageResponse?): Image {
        return Image(
            medium = response?.medium ?: ""
        )
    }
}