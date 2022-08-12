package com.abdelnabi618.articlestask.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.abdelnabi618.articlestask.model.ArticlesModel
import com.abdelnabi618.articlestask.utils.Constants.DATABASE_VERSION
import com.abdelnabi618.articlestask.utils.ListToStringConverters

@Database(entities = [ArticlesModel::class], version = DATABASE_VERSION)
@TypeConverters(ListToStringConverters::class)
abstract class ArticlesDatabase : RoomDatabase() {
    abstract fun getArticlesDao(): ArticlesDao
}