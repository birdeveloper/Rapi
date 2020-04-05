package com.birdeveloper.rapi

import java.util.WeakHashMap
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
/**
Created by @birdeveloper , see profile link: https://github.com/birdeveloper
Created date: 04.05.2020
Author: Görkem KARA - gorkemkara.com.tr
 */
object RestCreator {
    val params:WeakHashMap<String, Any>
        get() {
            return ParamsHolder.PARAMS
        }
    val restService:RestService
        get() {
            return RestServiceHolder.REST_SERVICE
        }
    object ParamsHolder {
        val PARAMS = WeakHashMap<String, Any>()
    }
    private object RetrofitHolder {
        private val BASE_URL = Rapi.baseUrl
        private var headers: WeakHashMap<String, String>? = Rapi.getHeaders
        private var timeout: Int? = Rapi.timeout
        val RETROFIT_CLIENT = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OKHttpHolder.clientBuilder(headers, timeout!!))
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
    }
    private object OKHttpHolder {

        fun clientBuilder(headers: WeakHashMap<String, String>? , timeout: Int): OkHttpClient{
            val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
            val clientBuilder = OkHttpClient.Builder()
            if (BuildConfig.DEBUG) {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
                clientBuilder.addInterceptor(httpLoggingInterceptor)
            }
            if (headers != null){
                clientBuilder.addInterceptor { chain ->
                    val newRequest:okhttp3.Request.Builder = chain.request().newBuilder()
                    for (item in headers.entries){
                        newRequest.addHeader(
                            item.key, item.value
                        ).build()
                    }
                    chain.proceed(newRequest.build())
                }
            }
            clientBuilder.readTimeout(timeout.toLong(), TimeUnit.SECONDS)
            clientBuilder.writeTimeout(timeout.toLong(), TimeUnit.SECONDS)
            clientBuilder.connectTimeout(timeout.toLong(), TimeUnit.SECONDS)
            return clientBuilder.build()

        }
    }
    private object RestServiceHolder {
        val REST_SERVICE = RetrofitHolder.RETROFIT_CLIENT.create(RestService::class.java)
    }
}