package com.bertholucci.domain.model

data class Show(
    val id: Int,
    val name: String,
    val language: String,
    val genres: List<String>,
    val status: String,
    val averageRuntime: String,
    val premiered: String,
    val ended: String,
    val schedule: Schedule,
    val rating: Rating,
    val summary: String,
    val image: Image,
    val embedded: EpisodesEmbedded
)

data class Schedule(
    val time: String,
    val days: List<String>
)

data class Rating(
    val average: String
)

data class Image(
    val medium: String
)

data class EpisodesEmbedded(
    val episodes: List<Episode>
)

data class Episode(
    val id: Int,
    val name: String,
    val number: String,
    val season: String,
    val airDate: String,
    val airTime: String,
    val rating: Rating,
    val summary: String,
    val image: Image
)