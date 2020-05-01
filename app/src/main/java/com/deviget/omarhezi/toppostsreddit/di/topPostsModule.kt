package com.deviget.omarhezi.toppostsreddit.di

import com.deviget.omarhezi.toppostsreddit.repositories.RedditRepository
import com.deviget.omarhezi.toppostsreddit.repositories.RedditRepositoryImpl
import com.deviget.omarhezi.toppostsreddit.usecase.GetTopPosts
import com.deviget.omarhezi.toppostsreddit.viewmodel.TopPostsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val topPostsModule = module {

    single<RedditRepository> { RedditRepositoryImpl(get()) }

    factory { GetTopPosts(get()) }

    viewModel { TopPostsViewModel(get()) }
}