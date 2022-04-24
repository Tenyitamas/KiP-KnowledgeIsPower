package com.tenyitamas.kip_knowledgeispower.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.tenyitamas.kip_knowledgeispower.data.local.NewsDatabase
import com.tenyitamas.kip_knowledgeispower.data.preferences.DefaultPreferences
import com.tenyitamas.kip_knowledgeispower.data.remote.NewsApi
import com.tenyitamas.kip_knowledgeispower.data.repository.NewsRepositoryImpl
import com.tenyitamas.kip_knowledgeispower.domain.preferences.Preferences
import com.tenyitamas.kip_knowledgeispower.domain.repository.NewsRepository
import com.tenyitamas.kip_knowledgeispower.domain.use_case.*
import com.tenyitamas.kip_knowledgeispower.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideSharedPreferences(
        app: Application
    ): SharedPreferences {
        return app.getSharedPreferences("shared_pref", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun providePreferences(sharedPreferences: SharedPreferences): Preferences {
        return DefaultPreferences(sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsApi (client: OkHttpClient): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(app: Application): NewsDatabase {
        return Room.databaseBuilder(
            app,
            NewsDatabase::class.java,
            "news_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        db: NewsDatabase,
        api: NewsApi
    ): NewsRepository {
        return NewsRepositoryImpl(
            dao = db.dao,
            api = api
        )
    }

    @Provides
    @Singleton
    fun provideNewsUseCases(
        repository: NewsRepository
    ): NewsUseCases {
        return NewsUseCases(
            deleteArticle = DeleteArticle(repository),
            getSavedArticle = GetSavedArticle(repository),
            getTopNews = GetTopNews(repository),
            saveArticle = SaveArticle(repository),
            searchNews = SearchNews(repository)
        )
    }
}