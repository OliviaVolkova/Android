package com.example.application.data.retrofit


import com.example.application.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {

    private const val QUERY_LANG = "lang"
    private const val QUERY_API_KEY = "appid"
    private const val QUERY_UNITS = "units"

    private val apiKeyInterceptor = Interceptor{ chain ->
        val original = chain.request()
        original.url.newBuilder()
            .addQueryParameter(QUERY_API_KEY, BuildConfig.API_KEY)
            .build().let {
                chain.proceed(original.newBuilder().url(it).build())
        }
    }

    private val unitMetricRuInterceptor = Interceptor { chain ->
        val original = chain.request()
        original.url.newBuilder()
            .addQueryParameter(QUERY_UNITS, "metric")
            .addQueryParameter(QUERY_LANG, "ru")
            .build().let {
                chain.proceed(original.newBuilder().url(it).build())
            }
    }

    private val client by lazy{
        OkHttpClient.Builder()
            .addInterceptor(apiKeyInterceptor)
            .addInterceptor(unitMetricRuInterceptor)
            .addInterceptor(LoggingInterceptor())
            .build()
    }

    private val retrofit by lazy{
        Retrofit.Builder()
            .client(client)
            .baseUrl(BuildConfig.API_URI)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val weatherApi by lazy {
        retrofit.create(WeatherApi::class.java)
    }
}