package com.bertholucci.data.model

import com.google.gson.annotations.SerializedName

class ShowResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String?,
    @SerializedName("language") val language: String?,
    @SerializedName("genres") val genres: List<String>,
    @SerializedName("status") val status: String?,
    @SerializedName("averageRuntime") val averageRuntime: String?,
    @SerializedName("premiered") val premiered: String?,
    @SerializedName("ended") val ended: String?,
    @SerializedName("schedule") val schedule: ScheduleResponse?,
    @SerializedName("rating") val rating: RatingResponse?,
    @SerializedName("summary") val summary: String?,
    @SerializedName("image") val image: ImageResponse?,
    @SerializedName("_embedded") val embedded: EpisodesEmbeddedResponse?
)

class ScheduleResponse(
    @SerializedName("time") val time: String?,
    @SerializedName("days") val days: List<String>?
)

class RatingResponse(
    @SerializedName("average") val average: String?
)

class ImageResponse(
    @SerializedName("medium") val medium: String?
)

class EpisodesEmbeddedResponse(
    @SerializedName("episodes") val episodes: List<EpisodeResponse>?
)

class EpisodeResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String?,
    @SerializedName("number") val number: Int?,
    @SerializedName("season") val season: Int?,
    @SerializedName("runtime") val runtime: String?,
    @SerializedName("airDate") val airDate: String?,
    @SerializedName("airTime") val airTime: String?,
    @SerializedName("rating") val rating: RatingResponse?,
    @SerializedName("summary") val summary: String?,
    @SerializedName("image") val image: ImageResponse?
)
