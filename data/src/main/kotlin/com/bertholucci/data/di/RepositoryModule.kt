package com.bertholucci.data.di

import com.bertholucci.data.repository.ShowRepositoryImpl
import com.bertholucci.domain.repository.ShowRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<ShowRepository> { ShowRepositoryImpl(api = get()) }
}
