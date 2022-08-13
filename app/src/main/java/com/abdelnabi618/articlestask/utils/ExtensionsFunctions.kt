package com.abdelnabi618.articlestask.utils

import com.abdelnabi618.articlestask.model.ArticlesModel
import com.abdelnabi618.articlestask.model.ArticlesNetworkModel

fun listOfNetworkArticlesToEntity(listOfNetworkArticles: List<ArticlesNetworkModel>): List<ArticlesModel>{
    val result = mutableListOf<ArticlesModel>()
    listOfNetworkArticles.forEach{
        result.add(it.toArticleModel())
    }
    return result
}