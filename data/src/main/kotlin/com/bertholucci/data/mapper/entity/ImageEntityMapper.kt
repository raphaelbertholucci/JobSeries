package com.bertholucci.data.mapper.entity

import com.bertholucci.data.mapper.BaseMapper
import com.bertholucci.data.model.ImageEntity
import com.bertholucci.domain.model.Image

object ImageEntityMapper : BaseMapper<ImageEntity?, Image> {
    override fun mapFromDomain(domain: Image): ImageEntity {
        return ImageEntity(
            medium = domain.medium
        )
    }

    override fun mapToDomain(response: ImageEntity?): Image {
        return Image(
            medium = response?.medium ?: ""
        )
    }
}