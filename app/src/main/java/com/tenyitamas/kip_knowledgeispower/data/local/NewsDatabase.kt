package com.tenyitamas.kip_knowledgeispower.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tenyitamas.kip_knowledgeispower.data.local.entity.ArticleEntity

@Database(
    entities = [ArticleEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class NewsDatabase: RoomDatabase() {
    abstract val dao: NewsDao
}