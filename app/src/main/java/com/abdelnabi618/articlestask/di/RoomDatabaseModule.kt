package com.abdelnabi618.articlestask.di

import android.content.Context
import androidx.room.Room
import com.abdelnabi618.articlestask.data.local.room.ArticlesDao
import com.abdelnabi618.articlestask.data.local.room.ArticlesDatabase
import com.abdelnabi618.articlestask.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RoomDatabaseModule {

    @Singleton
    @Provides
    fun provideArticlesDatabase(
        @ApplicationContext app: Context
    ): ArticlesDatabase = Room.databaseBuilder(
        app,
        ArticlesDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideArticlesDao(articlesDatabase: ArticlesDatabase): ArticlesDao =
        articlesDatabase.getArticlesDao()
}