package com.bertholucci.data.di

import com.bertholucci.data.repository.HomeRepositoryImpl
import com.bertholucci.domain.repository.HomeRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<HomeRepository> { HomeRepositoryImpl(api = get()) }
}
