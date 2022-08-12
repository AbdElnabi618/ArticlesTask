package com.abdelnabi618.articlestask.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.abdelnabi618.articlestask.model.ArticlesModel

@Database(entities = [ArticlesModel::class], version = 1)
abstract class ArticlesDatabase : RoomDatabase() {
    abstract fun ArticlesDao(): ArticlesDao
}