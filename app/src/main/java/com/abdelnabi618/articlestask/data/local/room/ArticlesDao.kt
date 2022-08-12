package com.abdelnabi618.articlestask.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.abdelnabi618.articlestask.model.ArticlesModel

@Dao
interface ArticlesDao {

    @Query("SELECT * FROM Articles")
    suspend fun getAllArticles(): List<ArticlesModel>

    @Insert
    suspend fun insertAll(articles: List<ArticlesModel>)

    @Query("DELETE FROM Articles")
    suspend fun deleteAllCashedArticles()
}