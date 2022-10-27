package com.rappi.core.di

import com.rappi.core.BuildConfig
import com.rappi.core.BuildConfig.CORE_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object EMovieNetworkModule {

    private const val MAX_REQUEST_COUNT = 5
    private const val TIME_OUT_CONNECT = 20L
    private const val TIME_OUT_WRITE = 20L
    private const val TIME_OUT_READ = 20L

    @Singleton
    @Provides
    fun provideCoreRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
    ): Retrofit = Retrofit.Builder().baseUrl(CORE_BASE_URL).addConverterFactory(converterFactory)
        .client(okHttpClient).build()

    @Singleton
    @Provides
    fun provideCoreConverter(): Converter.Factory = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideCoreOkHttpClient(
        dispatcher: Dispatcher,
        loggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient = OkHttpClient.Builder().connectTimeout(TIME_OUT_CONNECT, TimeUnit.SECONDS)
        .writeTimeout(TIME_OUT_WRITE, TimeUnit.SECONDS).readTimeout(TIME_OUT_READ, TimeUnit.SECONDS)
        .dispatcher(dispatcher).apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(loggingInterceptor)
                addInterceptor {
                    it.proceed(it.request())
                }
            }
        }.build()

    @Singleton
    @Provides
    fun provideCoreHttpLoggingInterceptor() =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    @Singleton
    @Provides
    fun provideCoreDispatcher() = Dispatcher().apply { maxRequests = MAX_REQUEST_COUNT }
}