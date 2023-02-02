package com.bertholucci.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shows")
data class ShowEntity(
    @PrimaryKey var id: Int = 0,
    val name: String = "",
    val language: String = "",
    val genres: String = "",
    val status: String = "",
    val averageRuntime: String = "",
    val premiered: String = "",
    val ended: String = "",
    val schedule: String = "",
    val rating: String = "",
    val summary: String = "",
    val image: String = "",
    val episodes: String = ""
)

@Entity(tableName = "episodes")
data class EpisodeEntity(
    @PrimaryKey val id: Int = 0,
    val name: String = "",
    val number: Int = 0,
    val season: Int = 0,
    val runtime: String = "",
    val airDate: String = "",
    val airTime: String = "",
    val rating: String = "",
    val summary: String = "",
    val image: String = ""
)

data class ScheduleEntity(
    val id: Int = 0,
    val time: String = "",
    val days: List<String> = listOf()
)

data class RatingEntity(
    val id: Int = 0,
    val average: String = ""
)

data class ImageEntity(
    val id: Int = 0,
    val medium: String = ""
)