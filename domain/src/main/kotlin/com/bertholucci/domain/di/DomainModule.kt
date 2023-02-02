package com.bertholucci.domain.di

import com.bertholucci.domain.interactor.GetEpisodeById
import com.bertholucci.domain.interactor.GetEpisodeByIdFromDB
import com.bertholucci.domain.interactor.GetShowById
import com.bertholucci.domain.interactor.GetShowByIdFromDB
import com.bertholucci.domain.interactor.GetShows
import com.bertholucci.domain.interactor.GetShowsByQuery
import com.bertholucci.domain.interactor.GetShowsFromDB
import com.bertholucci.domain.interactor.InsertEpisodeIntoDB
import com.bertholucci.domain.interactor.InsertShowIntoDB
import com.bertholucci.domain.interactor.RemoveEpisodeFromDB
import com.bertholucci.domain.interactor.RemoveShowIntoDB
import org.koin.dsl.module

val domainModule = module {
    factory { GetShows(repository = get()) }
    factory { GetShowsByQuery(repository = get()) }
    factory { GetShowById(repository = get()) }
    factory { GetEpisodeById(repository = get()) }
    factory { GetShowsFromDB(repository = get()) }
    factory { InsertShowIntoDB(repository = get()) }
    factory { InsertEpisodeIntoDB(repository = get()) }
    factory { RemoveShowIntoDB(repository = get()) }
    factory { RemoveEpisodeFromDB(repository = get()) }
    factory { GetShowByIdFromDB(repository = get()) }
    factory { GetEpisodeByIdFromDB(repository = get()) }
}