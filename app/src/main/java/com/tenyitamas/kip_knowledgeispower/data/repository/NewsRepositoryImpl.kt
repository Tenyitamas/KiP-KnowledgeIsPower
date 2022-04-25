package com.tenyitamas.kip_knowledgeispower.data.repository

import com.tenyitamas.kip_knowledgeispower.data.local.NewsDao
import com.tenyitamas.kip_knowledgeispower.data.mapper.toArticle
import com.tenyitamas.kip_knowledgeispower.data.mapper.toArticleEntity
import com.tenyitamas.kip_knowledgeispower.data.remote.NewsApi
import com.tenyitamas.kip_knowledgeispower.domain.model.Article
import com.tenyitamas.kip_knowledgeispower.domain.repository.NewsRepository
import com.tenyitamas.kip_knowledgeispower.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val dao: NewsDao,
    private val api: NewsApi
): NewsRepository{
    override suspend fun searchNews(query: String, page: Int): Resource<List<Article>> {
        return try {
            val response = api.searchNews(
                searchQuery = query,
                pageNumber = page
            )

            val result = response.body()
            if(response.isSuccessful && result != null) {
                Resource.Success(result.articles)
            } else {
                Resource.Error("Error while searching for news with query: $query")
            }
        } catch (e: Exception) {
            Resource.Error("Error occurred: ${e.localizedMessage}")
        }
    }

    override suspend fun getTopNews(countryCode: String, page: Int): Resource<List<Article>> {
        return try {
            val response = api.getTopNews(
                countryCode = countryCode,
                pageNumber = page
            )

            val result = response.body()
            if(response.isSuccessful && result != null) {
                Resource.Success(result.articles)
            } else {
                Resource.Error("Error while searching for news")
            }
        } catch (e: Exception) {
            Resource.Error("Error occurred: ${e.localizedMessage}")
        }
    }

    override suspend fun saveArticle(article: Article) {
        dao.insert(article = article.toArticleEntity())
    }

    override suspend fun deleteArticle(id: Int) {
        dao.delete(id = id)
    }

    override fun getSavedArticles(): Flow<List<Article>> {
        return dao.getSavedArticles().map { entities ->
            entities.map { it.toArticle() }
        }
    }
}