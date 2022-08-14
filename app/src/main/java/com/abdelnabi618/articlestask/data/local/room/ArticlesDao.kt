package com.abdelnabi618.articlestask.data.local.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.abdelnabi618.articlestask.model.ArticlesModel
import com.abdelnabi618.articlestask.utils.Constants.ARTICLES_TABLE_NAME

@Dao
interface ArticlesDao {

    @Query("SELECT * FROM $ARTICLES_TABLE_NAME ORDER BY databaseId")
    fun getAllArticles(): PagingSource<Int, ArticlesModel>

    @Query("SELECT * FROM $ARTICLES_TABLE_NAME WHERE apiId=:id LIMIT 1")
    suspend fun getArticle(id: Int): ArticlesModel


    @Query("SELECT * FROM $ARTICLES_TABLE_NAME WHERE apiId LIKE :id")
    fun filterArticles(id: Int): PagingSource<Int, ArticlesModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(articles: List<ArticlesModel>)

    @Query("DELETE FROM $ARTICLES_TABLE_NAME")
    suspend fun deleteAllCashedArticles()
}