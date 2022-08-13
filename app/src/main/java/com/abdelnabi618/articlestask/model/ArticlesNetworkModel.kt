package com.abdelnabi618.articlestask.model

import com.google.gson.annotations.SerializedName

data class ArticlesNetworkModel(
    @SerializedName("body")
    val body: String,
    @SerializedName("id")
    val apiId: Int,
    @SerializedName("reactions")
    val reactions: Int,
    @SerializedName("tags")
    val tags: List<String>,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: Int
) {
    fun toArticleModel(): ArticlesModel {
        return ArticlesModel(
            body,
            0,
            apiId,
            reactions,
            tags,
            title,
            userId
        )
    }
}