package com.co.gamesapp.data.source.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    @Volatile
    private var instance: Retrofit? = null

    fun getInstance(baseUrl: String, timeOut: Long): Retrofit? =
        instance ?: synchronized(this) {
            instance ?: buildRetrofit(baseUrl, timeOut).also { instance = it }
        }

    private fun buildRetrofit(baseUrl: String, timeOut: Long): Retrofit? {
        val httpClient = getHttpClient(timeOut)
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getHttpClient(timeOut: Long): OkHttpClient = OkHttpClient().newBuilder()
        .connectTimeout(timeOut, TimeUnit.SECONDS)
        .readTimeout(timeOut, TimeUnit.SECONDS)
        .writeTimeout(timeOut, TimeUnit.SECONDS)
        .addInterceptor {
            val request = it.request().newBuilder()
                .addHeader("X-Parse-GamesApplication-Id", "I9pG8SLhTzFA0ImFkXsEvQfXMYyn0MgDBNg10Aps")
                .addHeader("X-Parse-REST-API-Key", "Yvd2eK2LODfwVmkjQVNzFXwd3N0X7oUuwiMI3VDZ")
                .build()
            return@addInterceptor it.proceed(request)
        }
        .build()

}