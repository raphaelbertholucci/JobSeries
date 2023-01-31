package com.bertholucci.jobseries

import android.app.Application
import com.bertholucci.data.di.apiModule
import com.bertholucci.data.di.repositoryModule
import com.bertholucci.domain.di.domainModule
import com.bertholucci.home.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Application)
            androidLogger()
            modules(listOf(apiModule, repositoryModule, domainModule, viewModelModule))
        }
    }
}