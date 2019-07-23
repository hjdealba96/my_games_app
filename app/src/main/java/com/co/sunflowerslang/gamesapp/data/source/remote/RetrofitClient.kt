package com.co.sunflowerslang.gamesapp.data.source.remote

import com.co.sunflowerslang.gamesapp.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient private constructor() {
    companion object {

        fun newInstance(): Retrofit = buildRetrofit(BuildConfig.BASE_URL, BuildConfig.TIMEOUT_CONNECTION)

        private fun buildRetrofit(baseUrl: String, timeOut: Long): Retrofit {
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
                    .addHeader(BuildConfig.PARSE_APP_ID_HEADER, BuildConfig.PARSE_APP_ID_VALUE)
                    .addHeader(BuildConfig.PARSE_REST_API_HEADER, BuildConfig.PARSE_REST_API_KEY)
                    .build()
                return@addInterceptor it.proceed(request)
            }
            .build()

    }
}