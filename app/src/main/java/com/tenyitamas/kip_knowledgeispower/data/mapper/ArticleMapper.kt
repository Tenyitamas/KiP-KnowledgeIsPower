package com.tenyitamas.kip_knowledgeispower.data.mapper

import com.tenyitamas.kip_knowledgeispower.data.local.entity.ArticleEntity
import com.tenyitamas.kip_knowledgeispower.domain.model.Article
import com.tenyitamas.kip_knowledgeispower.domain.model.Source

fun Article.toArticleEntity(): ArticleEntity {
    return ArticleEntity(
        id = id,
        author = author ?: "",
        content = content ?: "",
        description = description ?: "",
        publishedAt = publishedAt ?: "",
        source = source ?: Source("", ""),
        title = title ?: "",
        url = url ?: "",
        urlToImage = urlToImage ?: ""
    )
}

fun ArticleEntity.toArticle(): Article {
    return Article(
        id = id,
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt,
        source = source,
        title = title,
        url = url,
        urlToImage = urlToImage
    )
}

