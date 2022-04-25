package com.tenyitamas.kip_knowledgeispower.data.local

import androidx.room.*
import com.tenyitamas.kip_knowledgeispower.data.local.entity.ArticleEntity
import com.tenyitamas.kip_knowledgeispower.domain.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: ArticleEntity)

    @Query("SELECT * FROM articles")
    fun getSavedArticles(): Flow<List<ArticleEntity>>

    @Query("DELETE FROM articles WHERE id=:id")
    suspend fun delete(id: Int)
}