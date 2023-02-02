package com.bertholucci.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bertholucci.data.model.ShowEntity

@Dao
interface DatabaseDao {

    @Query("SELECT * FROM shows")
    suspend fun getShows(): List<ShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShow(show: ShowEntity)

    @Delete
    suspend fun removeShow(show: ShowEntity)

    @Query("SELECT * FROM shows WHERE id=:id")
    suspend fun getShowByIDFromDB(id: Int): ShowEntity
}