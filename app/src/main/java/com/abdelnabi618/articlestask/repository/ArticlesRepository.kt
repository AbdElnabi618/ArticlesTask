package com.abdelnabi618.articlestask.repository

import androidx.paging.PagingData
import com.abdelnabi618.articlestask.model.ArticlesModel
import kotlinx.coroutines.flow.Flow

interface ArticlesRepository {

    suspend fun getArticles(): Flow<PagingData<ArticlesModel>>

    suspend fun getSingleArticle(articleId: Int): ArticlesModel
}