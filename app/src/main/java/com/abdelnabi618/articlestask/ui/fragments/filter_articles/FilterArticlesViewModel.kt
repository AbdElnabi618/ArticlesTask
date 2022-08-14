package com.abdelnabi618.articlestask.ui.fragments.filter_articles

import androidx.lifecycle.ViewModel
import com.abdelnabi618.articlestask.model.ArticlesModel
import com.abdelnabi618.articlestask.repository.ArticlesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

@HiltViewModel
class FilterArticlesViewModel @Inject constructor(
    private val articlesRepository: ArticlesRepository
) : ViewModel() {

    private val _articlesFlow: MutableSharedFlow<List<ArticlesModel>> = MutableSharedFlow()
    val articlesFlow: SharedFlow<List<ArticlesModel>> = _articlesFlow


    suspend fun filterArticles(articleId: Int) {
        val filterArticles = articlesRepository.filterArticles(articleId)
        _articlesFlow.emit(filterArticles)
    }

}