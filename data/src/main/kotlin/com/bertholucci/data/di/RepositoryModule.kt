package com.bertholucci.data.di

import com.bertholucci.data.repository.HomeRepositoryImpl
import com.bertholucci.data.repository.ShowRepositoryImpl
import com.bertholucci.domain.repository.HomeRepository
import com.bertholucci.domain.repository.ShowRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<HomeRepository> { HomeRepositoryImpl(api = get()) }
    factory<ShowRepository> { ShowRepositoryImpl(api = get()) }
}
