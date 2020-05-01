package com.deviget.omarhezi.toppostsreddit.di

import com.deviget.omarhezi.toppostsreddit.api.RedditApiService
import org.koin.dsl.module
import retrofit2.Retrofit

val serviceModule = module {
    single<RedditApiService> { provideService(get()) }
}

private inline fun <reified T> provideService(retrofit: Retrofit): T = retrofit.create(T::class.java)