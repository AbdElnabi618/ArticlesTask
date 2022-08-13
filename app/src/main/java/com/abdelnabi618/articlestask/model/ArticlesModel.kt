package com.abdelnabi618.articlestask.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.abdelnabi618.articlestask.utils.Constants.ARTICLES_TABLE_NAME
import com.abdelnabi618.articlestask.utils.ListToStringConverters
import com.google.gson.annotations.SerializedName

@Entity(tableName = ARTICLES_TABLE_NAME)
@TypeConverters(ListToStringConverters::class)
data class ArticlesModel(
    @ColumnInfo(name = "body")
    @SerializedName("body")
    val body: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "databaseId")
    val id: Int,

    @SerializedName("id")
    @ColumnInfo(name = "apiId")
    val apiId: Int,

    @SerializedName("reactions")
    @ColumnInfo(name = "reactions")
    val reactions: Int,

    @SerializedName("tags")
    @ColumnInfo(name = "tags")
    val tags: List<String>,

    @SerializedName("title")
    @ColumnInfo(name = "title")
    val title: String,

    @SerializedName("userId")
    @ColumnInfo(name = "userId")
    val userId: Int
)