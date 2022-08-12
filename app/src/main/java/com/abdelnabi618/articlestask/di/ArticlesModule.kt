package com.abdelnabi618.articlestask.di

import com.abdelnabi618.articlestask.data.remote.ArticlesApiCall
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ArticlesModule {

    @Provides
    @Singleton
    fun provideArticlesCall(retrofit: Retrofit): ArticlesApiCall =
        retrofit.create(ArticlesApiCall::class.java)


}