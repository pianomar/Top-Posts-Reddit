package com.deviget.omarhezi.toppostsreddit.di

import com.deviget.omarhezi.toppostsreddit.api.RedditApiService
import org.koin.dsl.module
import retrofit2.Retrofit

val serviceModule = module {
    single<RedditApiService> { provideService(get()) }
}

private fun provideService(retrofit: Retrofit) = retrofit.create(RedditApiService::class.java)