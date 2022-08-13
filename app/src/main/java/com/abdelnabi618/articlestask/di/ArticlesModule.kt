package com.abdelnabi618.articlestask.di

import com.abdelnabi618.articlestask.data.data_source.articles_paging.ArticlesRemoteMediator
import com.abdelnabi618.articlestask.data.data_source.articles.ArticlesDataSource
import com.abdelnabi618.articlestask.data.data_source.articles.ArticlesDataSourceImpl
import com.abdelnabi618.articlestask.data.local.room.ArticlesDao
import com.abdelnabi618.articlestask.data.local.room.ArticlesDatabase
import com.abdelnabi618.articlestask.data.remote.ArticlesApiCall
import com.abdelnabi618.articlestask.repository.ArticlesRepository
import com.abdelnabi618.articlestask.repository.ArticlesRepositoryImpl
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

    @Provides
    @Singleton
    fun provideArticlesDataSource(apiCall: ArticlesApiCall): ArticlesDataSource =
        ArticlesDataSourceImpl(apiCall)


    @Provides
    @Singleton
    fun provideArticleRemoteMediator(
        articlesDatabase: ArticlesDatabase,
        articlesDataSource: ArticlesDataSource
    ): ArticlesRemoteMediator =
        ArticlesRemoteMediator(
            articlesDatabase,
            articlesDataSource
        )

    @Provides
    @Singleton
    fun provideArticlesRepository(
        articlesRoomDao: ArticlesDao,
        articlesRemoteMediator: ArticlesRemoteMediator
    ): ArticlesRepository =
        ArticlesRepositoryImpl(articlesRoomDao, articlesRemoteMediator)
}