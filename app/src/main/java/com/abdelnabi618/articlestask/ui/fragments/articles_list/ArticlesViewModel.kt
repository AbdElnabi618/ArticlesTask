package com.abdelnabi618.articlestask.ui.fragments.articles_list

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.abdelnabi618.articlestask.model.ArticlesModel
import com.abdelnabi618.articlestask.repository.ArticlesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject constructor(
    private val articlesRepository: ArticlesRepository
): ViewModel() {



    suspend fun getAllArticles(): Flow<PagingData<ArticlesModel>> = articlesRepository.getArticles()
}