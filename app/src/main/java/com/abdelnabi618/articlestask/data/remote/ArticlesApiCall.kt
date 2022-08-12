package com.abdelnabi618.articlestask.data.remote

import com.abdelnabi618.articlestask.model.ArticlesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticlesApiCall {

    @GET("posts?skip={skip}&limit={limit}")
    suspend fun getArticles(
        @Query("skip") skip: Int,
        @Query("limit") limit: Int
    ): Response<ArticlesResponse>
}