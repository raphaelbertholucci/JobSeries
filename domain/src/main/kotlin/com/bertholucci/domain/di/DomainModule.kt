package com.bertholucci.domain.di

import com.bertholucci.domain.interactor.GetEpisodeById
import com.bertholucci.domain.interactor.GetShowById
import com.bertholucci.domain.interactor.GetShows
import com.bertholucci.domain.interactor.GetShowsByQuery
import org.koin.dsl.module

val domainModule = module {
    factory { GetShows(repository = get()) }
    factory { GetShowsByQuery(repository = get()) }
    factory { GetShowById(repository = get()) }
    factory { GetEpisodeById(repository = get()) }
}