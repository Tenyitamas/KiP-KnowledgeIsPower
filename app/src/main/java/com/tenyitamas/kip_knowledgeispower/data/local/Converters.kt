package com.tenyitamas.kip_knowledgeispower.data.local

import androidx.room.TypeConverter
import com.tenyitamas.kip_knowledgeispower.domain.model.Source

class Converters {

    @TypeConverter
    fun fromSource(source: Source): String {
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name, name)
    }
}