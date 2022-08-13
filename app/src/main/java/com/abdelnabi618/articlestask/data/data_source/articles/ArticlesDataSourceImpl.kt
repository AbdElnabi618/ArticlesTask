package com.abdelnabi618.articlestask.data.data_source.articles

import android.util.Log
import com.abdelnabi618.articlestask.data.local.room.ArticlesDao
import com.abdelnabi618.articlestask.data.remote.ArticlesApiCall
import com.abdelnabi618.articlestask.model.ArticlesResponse
import retrofit2.Response
import javax.inject.Inject

class ArticlesDataSourceImpl @Inject constructor(
    private val articlesApiCall: ArticlesApiCall
) : ArticlesDataSource {

    @Throws(Exception::class)
    override suspend fun getArticles(skip: Int, limit: Int): Response<ArticlesResponse> =
        articlesApiCall.getArticles(skip, limit)


}