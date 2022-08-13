package com.abdelnabi618.articlestask.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.abdelnabi618.articlestask.data.data_source.articles_paging.ArticlesRemoteMediator
import com.abdelnabi618.articlestask.data.local.room.ArticlesDao
import com.abdelnabi618.articlestask.model.ArticlesModel
import com.abdelnabi618.articlestask.utils.Constants.DATA_LIMIT
import com.abdelnabi618.articlestask.utils.Constants.DATA_SKIP_INIT
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class ArticlesRepositoryImpl @Inject constructor(
    private val articlesDao: ArticlesDao,
    private val articlesRemoteMediator: ArticlesRemoteMediator
) : ArticlesRepository {

    override suspend fun getArticles(): Flow<PagingData<ArticlesModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = DATA_LIMIT,
                enablePlaceholders = false,
                initialLoadSize = DATA_LIMIT
            ),
            remoteMediator = articlesRemoteMediator,
            pagingSourceFactory = {
                articlesDao.getAllArticles()
            }, initialKey = DATA_SKIP_INIT
        ).flow
    }


}