package com.abdelnabi618.articlestask.di

import com.abdelnabi618.articlestask.data.remote.ArticlesApiCall
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

class ArticlesModule {



    @Provides
    @Singleton
    fun provideArticlesCall(retrofit: Retrofit) : ArticlesApiCall =
        retrofit.create(ArticlesApiCall::class.java)


}