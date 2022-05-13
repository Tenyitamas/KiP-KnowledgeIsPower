package com.tenyitamas.kip_knowledgeispower.navigation

import android.os.Bundle
import androidx.navigation.NavType
import com.google.gson.Gson
import com.tenyitamas.kip_knowledgeispower.domain.model.Article

class ArticleParamType : NavType<Article>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): Article? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): Article {

        return Gson().fromJson(value, Article::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: Article) {
        bundle.putParcelable(key, value)
    }
}