package com.birdeveloper.rapi

import java.util.WeakHashMap
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.QueryMap
import retrofit2.http.Streaming
import retrofit2.http.Url

/**
Created by @birdeveloper , see profile link: https://github.com/birdeveloper
Created date: 04.05.2020
Author: Görkem KARA - gorkemkara.com.tr
 */

interface RestService {
    @GET
    fun get(@Url url:String, @QueryMap params:WeakHashMap<String, Any>):Call<String>
    @FormUrlEncoded
    @POST
    fun post(@Url url:String, @FieldMap params:WeakHashMap<String, Any>):Call<String>
    @POST
    fun postRaw(@Url url:String, @Body body:RequestBody):Call<String>
    @FormUrlEncoded
    @PUT
    fun put(@Url url:String, @QueryMap params:WeakHashMap<String, Any>):Call<String>
    @POST
    fun putRaw(@Url url:String, @Body body:RequestBody):Call<String>
    @DELETE
    fun delete(@Url url:String, @QueryMap params:WeakHashMap<String, Any>):Call<String>
    @Streaming
    @GET
    fun download(@Url url:String, @QueryMap params:WeakHashMap<String, Any>):Call<ResponseBody>
    @Multipart
    @POST
    fun upload(@Url url:String, @Part file:MultipartBody.Part):Call<String>
}