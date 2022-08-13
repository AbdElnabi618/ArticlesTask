package com.abdelnabi618.articlestask.data.data_source.articles

import com.abdelnabi618.articlestask.model.ArticlesResponse
import retrofit2.Response

interface ArticlesDataSource {

    suspend fun getArticles(
        skip: Int,
        limit: Int
    ):Response<ArticlesResponse>
}