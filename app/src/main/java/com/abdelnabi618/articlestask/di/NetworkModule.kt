package com.abdelnabi618.articlestask.di

import com.abdelnabi618.articlestask.BuildConfig
import com.abdelnabi618.articlestask.utils.Constants.BASE_URL
import com.google.gson.GsonBuilder
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = if (BuildConfig.DEBUG) {
        OkHttpClient.Builder().addInterceptor(
            LoggingInterceptor.Builder()
                .setLevel(Level.BASIC)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build()
        ).build()
    } else {
        OkHttpClient.Builder().build()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            )
        ).client(okHttpClient).build()

}