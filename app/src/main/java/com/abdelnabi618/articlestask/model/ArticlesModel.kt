package com.abdelnabi618.articlestask.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.abdelnabi618.articlestask.utils.Constants.ARTICLES_TABLE_NAME
import com.abdelnabi618.articlestask.utils.ListToStringConverters

@Entity(tableName = ARTICLES_TABLE_NAME)
@TypeConverters(ListToStringConverters::class)
data class ArticlesModel(
    @ColumnInfo(name = "body")
    val body: String,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "databaseId")
    val id: Int,
    @ColumnInfo(name = "apiId")
    val apiId: Int,
    @ColumnInfo(name = "reactions")
    val reactions: Int,
    @ColumnInfo(name = "tags")
    val tags: List<String>,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "userId")
    val userId: Int
)