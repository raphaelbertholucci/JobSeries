package com.bertholucci.data.di

import android.app.Application
import androidx.room.Room
import com.bertholucci.data.database.Database
import com.bertholucci.data.database.DatabaseDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

const val DATABASE_NAME = "app-db"

val databaseModule = module {

    fun provideDatabase(application: Application): Database {
        return Room.databaseBuilder(application, Database::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideDao(database: Database): DatabaseDao {
        return database.dao()
    }

    single { provideDatabase(androidApplication()) }
    single { provideDao(get()) }
}