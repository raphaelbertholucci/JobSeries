package com.bertholucci.data

import com.bertholucci.data.model.EpisodeResponse
import com.bertholucci.data.model.SearchResponse
import com.bertholucci.data.model.ShowResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JobSeriesApi {

    @GET("shows")
    suspend fun getShows(
        @Query("page") page: Int
    ): List<ShowResponse>

    @GET("search/shows")
    suspend fun getShowsByQuery(
        @Query("q") query: String
    ): List<SearchResponse>

    @GET("shows/{id}")
    suspend fun getShowById(
        @Path("id") id: Int,
        @Query("embed") embed: String = "episodes"
    ): ShowResponse

    @GET("episodes/{id}")
    suspend fun getEpisodeById(@Path("id") id: Int): EpisodeResponse
}