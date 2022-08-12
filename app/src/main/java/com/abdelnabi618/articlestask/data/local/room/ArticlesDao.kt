package com.abdelnabi618.articlestask.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.abdelnabi618.articlestask.model.ArticlesModel
import com.abdelnabi618.articlestask.utils.Constants.ARTICLES_TABLE_NAME

@Dao
interface ArticlesDao {

    @Query("SELECT * FROM $ARTICLES_TABLE_NAME")
    suspend fun getAllArticles(): List<ArticlesModel>

    @Insert
    suspend fun insertAll(articles: List<ArticlesModel>)

    @Query("DELETE FROM $ARTICLES_TABLE_NAME")
    suspend fun deleteAllCashedArticles()
}