package com.bertholucci.data

import com.bertholucci.data.model.EpisodeResponse
import com.bertholucci.data.model.ShowResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JobSeriesApi {

    @GET("shows")
    suspend fun getShows(@Query("page") page: Int): List<ShowResponse>

    @GET("shows/{id}")
    suspend fun getShowsById(@Path("id") id: Int): ShowResponse

    @GET("episodes/{id}")
    suspend fun getEpisodesById(@Path("id") id: Int): EpisodeResponse
}