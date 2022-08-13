package com.abdelnabi618.articlestask.data.data_source.articles_paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.abdelnabi618.articlestask.data.data_source.articles.ArticlesDataSource
import com.abdelnabi618.articlestask.data.local.room.ArticlesDatabase
import com.abdelnabi618.articlestask.model.ArticlesModel
import com.abdelnabi618.articlestask.utils.Constants.DATA_LIMIT
import com.abdelnabi618.articlestask.utils.Constants.DATA_SKIP_INIT
import com.abdelnabi618.articlestask.utils.listOfNetworkArticlesToEntity
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


@OptIn(ExperimentalPagingApi::class)
class ArticlesRemoteMediator @Inject constructor(
    private val database: ArticlesDatabase,
    private val articlesDataSource: ArticlesDataSource,
) : RemoteMediator<Int, ArticlesModel>() {
    val articlesDao = database.getArticlesDao()

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
        /*val cacheTimeout = TimeUnit.HOURS.convert(1, TimeUnit.MILLISECONDS)
        return if (System.currentTimeMillis() - articlesDao.lastUpdated() >= cacheTimeout) {
            // Cached data is up-to-date, so there is no need to re-fetch from network.
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            // Need to refresh cached data from network; returning LAUNCH_INITIAL_REFRESH here
            // will also block ArticlesRemoteMediator's APPEND and PREPEND from running until REFRESH
            // succeeds.
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }*/
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ArticlesModel>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> null

                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                        ?: return MediatorResult.Success(endOfPaginationReached = true)

                    lastItem.apiId
                }
            }

            val response = articlesDataSource.getArticles(loadKey ?: DATA_SKIP_INIT, DATA_LIMIT)

            if (response.isSuccessful) {
                val articlesResponse = response.body()!!
                database.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        articlesDao.deleteAllCashedArticles()
                    }

                    articlesDao.insertAll(listOfNetworkArticlesToEntity(articlesResponse.posts))
                }
            }

            MediatorResult.Success(endOfPaginationReached = response.body()!!.posts.last().apiId >= response.body()!!.total)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}