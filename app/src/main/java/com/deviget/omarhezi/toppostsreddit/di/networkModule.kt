package com.deviget.omarhezi.toppostsreddit.di

import android.util.Log
import com.deviget.omarhezi.toppostsreddit.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val networkModule = module {
    single { provideHttpLoggingInterceptor() }
    single(named("rawJsonInterceptor")) { provideRawJsonInterceptor() }
    single { provideOkHttpClient(get(), get(named("rawJsonInterceptor"))) }
    single { provideRetrofit(get()) }
}

private fun provideOkHttpClient(
    interceptor: HttpLoggingInterceptor,
    rawJsonInterceptor: Interceptor
) =
    OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(rawJsonInterceptor).build()

private fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val httpLoggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
        override fun log(message: String) {
            Log.d("okHttp:", message)
        }
    })

    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

    return httpLoggingInterceptor
}

private fun provideRawJsonInterceptor(): Interceptor {
    return object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            var request: Request = chain.request()
            val url = request.url.newBuilder().addQueryParameter("raw_json", "1").build()
            request = request.newBuilder().url(url).build()
            return chain.proceed(request)
        }
    }
}

private fun provideRetrofit(
    client: OkHttpClient
): Retrofit {
    val builder = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)

    return builder.build()
}