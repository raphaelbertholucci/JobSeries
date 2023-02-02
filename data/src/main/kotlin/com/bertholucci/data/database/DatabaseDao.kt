package com.bertholucci.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bertholucci.data.model.EpisodeEntity
import com.bertholucci.data.model.ShowEntity
import com.bertholucci.domain.model.Episode

@Dao
interface DatabaseDao {

    @Query("SELECT * FROM shows")
    suspend fun getShows(): List<ShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShow(show: ShowEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEpisode(episode: EpisodeEntity)

    @Delete
    suspend fun removeShow(show: ShowEntity)

    @Delete
    suspend fun removeEpisode(episode: EpisodeEntity)

    @Query("SELECT * FROM shows WHERE id=:id")
    suspend fun getShowByIDFromDB(id: Int): ShowEntity

    @Query("SELECT * FROM episodes WHERE id=:id")
    suspend fun getEpisodeByIDFromDB(id: Int): EpisodeEntity
}