package com.deviget.omarhezi.toppostsreddit

import android.app.Application
import com.deviget.omarhezi.toppostsreddit.di.networkModule
import com.deviget.omarhezi.toppostsreddit.di.serviceModule
import com.deviget.omarhezi.toppostsreddit.di.topPostsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

@Suppress("unused")
class TopPostsRedditApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initDI()
    }

    private fun initDI() {
        startKoin {
            androidLogger()
            androidContext(this@TopPostsRedditApplication)
            modules(
                networkModule,
                serviceModule,
                topPostsModule
            )
        }
    }
}