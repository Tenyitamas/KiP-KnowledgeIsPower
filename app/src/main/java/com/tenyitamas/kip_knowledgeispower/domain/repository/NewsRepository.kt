package com.tenyitamas.kip_knowledgeispower.domain.repository

import com.tenyitamas.kip_knowledgeispower.data.local.NewsDao
import com.tenyitamas.kip_knowledgeispower.data.remote.NewsApi
import com.tenyitamas.kip_knowledgeispower.domain.model.Article
import com.tenyitamas.kip_knowledgeispower.util.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository{

    suspend fun searchNews(
        query: String,
        page: Int
    ): Resource<List<Article>>

    suspend fun getTopNews(
        countryCode: String,
        page: Int
    ): Resource<List<Article>>

    suspend fun getNews(
        countryCode: String,
        page: Int
    ): Resource<List<Article>>

    suspend fun saveArticle(article: Article)

    suspend fun deleteArticle(article: Article)

    fun getSavedArticles(): Flow<List<Article>>
}