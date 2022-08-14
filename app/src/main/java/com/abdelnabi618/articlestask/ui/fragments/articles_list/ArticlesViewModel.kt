package com.abdelnabi618.articlestask.ui.fragments.articles_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.abdelnabi618.articlestask.model.ArticlesModel
import com.abdelnabi618.articlestask.repository.ArticlesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject constructor(
    private val articlesRepository: ArticlesRepository
) : ViewModel() {


    suspend fun getAllArticles(): Flow<PagingData<ArticlesModel>> =
        articlesRepository.getArticles().cachedIn(viewModelScope)

    suspend fun filterArticles(articleId: Int): Flow<PagingData<ArticlesModel>> =
        articlesRepository.filterArticles(articleId).cachedIn(viewModelScope)
}