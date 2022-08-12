package com.abdelnabi618.articlestask.model


import com.google.gson.annotations.SerializedName

data class ArticlesResponse(
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("posts")
    val posts: List<ArticlesModel>,
    @SerializedName("skip")
    val skip: String,
    @SerializedName("total")
    val total: Int
)