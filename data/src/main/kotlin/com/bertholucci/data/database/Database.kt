package com.bertholucci.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bertholucci.data.model.EpisodeEntity
import com.bertholucci.data.model.ShowEntity

@Database(entities = [ShowEntity::class, EpisodeEntity::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {
    abstract fun dao(): DatabaseDao
}