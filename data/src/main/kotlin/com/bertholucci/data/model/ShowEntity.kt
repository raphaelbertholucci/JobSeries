package com.bertholucci.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shows")
data class ShowEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
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

@Entity(tableName = "schedules")
data class ScheduleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val time: String = "",
    val days: List<String> = listOf()
)

@Entity(tableName = "ratings")
data class RatingEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val average: String = ""
)

@Entity(tableName = "images")
data class ImageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val medium: String = ""
)

@Entity(tableName = "episodes")
data class EpisodeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "",
    val number: Int = 0,
    val season: Int = 0,
    val runtime: String = "",
    val airDate: String = "",
    val airTime: String = "",
    val rating: RatingEntity = RatingEntity(),
    val summary: String = "",
    val image: ImageEntity = ImageEntity()
)