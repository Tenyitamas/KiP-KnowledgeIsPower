package com.tenyitamas.kip_knowledgeispower.data.remote

import com.tenyitamas.kip_knowledgeispower.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/top-headlines")
    suspend fun getTopNews(
        @Query("country")
        countryCode: String = "us",
        @Query("page")
        pageNumber: Int = 1,
        @Query("pageSize")
        pageSize: Int = 50,
        @Query("apiKey")
        apiKey: String = API_KEY
    ) : Response<NewsResponse>

    @GET("v2/everything")
    suspend fun searchNews(
        @Query("q")
        searchQuery: String,
        @Query("page")
        pageNumber: Int = 1,
        @Query("pageSize")
        pageSize: Int = 50,
        @Query("apiKey")
        apiKey: String = API_KEY
    ) : Response<NewsResponse>
}