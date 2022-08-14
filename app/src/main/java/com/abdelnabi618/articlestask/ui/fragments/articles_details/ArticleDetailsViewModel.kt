package com.abdelnabi618.articlestask.ui.fragments.articles_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdelnabi618.articlestask.model.ArticlesModel
import com.abdelnabi618.articlestask.repository.ArticlesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleDetailsViewModel @Inject constructor(
    private val articlesRepository: ArticlesRepository
) : ViewModel() {

    private val _articleStateFlow: MutableStateFlow<ArticlesModel?> by lazy {
        MutableStateFlow(null)
    }
    val articleStateFlow: StateFlow<ArticlesModel?> by lazy { _articleStateFlow }


    private val _loadingStateFlow: MutableStateFlow<Boolean> by lazy {
        MutableStateFlow(false)
    }
    val loadingStateFlow: StateFlow<Boolean> by lazy { _loadingStateFlow }

    fun getSingleArticle(articleId: Int) {
        viewModelScope.launch {
            _loadingStateFlow.emit(true)
            _articleStateFlow.emit(articlesRepository.getSingleArticle(articleId))
            _loadingStateFlow.emit(false)
        }
    }
}