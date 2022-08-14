package com.abdelnabi618.articlestask.ui.fragments.articles_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.abdelnabi618.articlestask.model.ArticlesModel
import com.abdelnabi618.articlestask.repository.ArticlesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.emitAll
import javax.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject constructor(
    private val articlesRepository: ArticlesRepository
) : ViewModel() {

    private val _articlesFlow: MutableSharedFlow<PagingData<ArticlesModel>> = MutableSharedFlow()
    val articlesFlow: SharedFlow<PagingData<ArticlesModel>> = _articlesFlow

    suspend fun getAllArticles() {
        _articlesFlow.emitAll(articlesRepository.getArticles().cachedIn(viewModelScope))
    }

}