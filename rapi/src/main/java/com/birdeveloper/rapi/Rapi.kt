package com.birdeveloper.rapi

import android.content.Context
import com.birdeveloper.rapi.callback.*
import com.birdeveloper.rapi.download.DownloadHandler
import java.io.File
import java.util.WeakHashMap
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
/**
Created by @birdeveloper , see profile link: https://github.com/birdeveloper
Created date: 04.05.2020
Author: Görkem KARA - gorkemkara.com.tr
 */
class Rapi internal constructor(
    url: String?,
    params:WeakHashMap<String, Any>,
    downloadDir: String?,
    extension: String?,
    name: String?,
    request: IRequest?,
    success: ISuccess?,
    failure: IFailure?,
    error: IError?,
    body: RequestBody?,
    file: File?,
    context: Context?
) {
    private var URL:String? = null
    private var REQUEST:IRequest? = null
    private var DOWNLOAD_DIR:String? = null
    private var EXTENSION:String? = null
    private var NAME:String? = null
    private var SUCCESS:ISuccess? = null
    private var FAILURE:IFailure? = null
    private var ERROR:IError? = null
    private var BODY:RequestBody? = null
    private var FILE:File? = null
    private var CONTEXT:Context? = null

    init{
        if (url != null) {
            this.URL = url
        }
        PARAMS.putAll(params)
        if (downloadDir != null) {
            this.DOWNLOAD_DIR = downloadDir
        }
        if (extension != null) {
            this.EXTENSION = extension
        }
        if (name != null) {
            this.NAME = name
        }
        if (request != null) {
            this.REQUEST = request
        }
        if (success != null) {
            this.SUCCESS = success
        }
        if (failure != null) {
            this.FAILURE = failure
        }
        if (error != null) {
            this.ERROR = error
        }
        if (body != null) {
            this.BODY = body
        }
        if (file != null) {
            this.FILE = file
        }
        if (context != null) {
            this.CONTEXT = context
        }
    }
    private fun request(method: HttpMethod) {
        val service = RestCreator.restService
        var call: Call<String>? = null
        if (REQUEST != null)
        {
            REQUEST!!.onRequestStart()
        }
        when (method) {
            HttpMethod.GET -> call = service.get(URL!!, PARAMS)
            HttpMethod.POST -> call = service.post(URL!!, PARAMS)
            HttpMethod.POST_RAW -> call = service.postRaw(URL!!, BODY!!)
            HttpMethod.PUT -> call = service.put(URL!!, PARAMS)
            HttpMethod.PUT_RAW -> call = service.putRaw(URL!!, BODY!!)
            HttpMethod.DELETE -> call = service.delete(URL!!, PARAMS)
            HttpMethod.UPLOAD -> {
                val requestBody = RequestBody.create(MultipartBody.FORM.toString().toMediaTypeOrNull(), FILE!!)
                val body = MultipartBody.Part.createFormData("file", (FILE as File).getName(), requestBody)
                call = RestCreator.restService.upload(URL!!, body)
            }
            else -> {}
        }
        if (call != null)
        {
            call.enqueue(RequestCallBacks(REQUEST, SUCCESS, FAILURE, ERROR))
        }
    }
    fun get() {
        request(HttpMethod.GET)
    }
    fun post() {
        if (BODY == null)
        {
            request(HttpMethod.POST)
        }
        else
        {
            if (!PARAMS.isEmpty())
            {
                throw RuntimeException("params must be null")
            }
            request(HttpMethod.POST_RAW)
        }
    }
    fun put() {
        if (BODY == null)
        {
            request(HttpMethod.PUT)
        }
        else
        {
            if (!PARAMS.isEmpty())
            {
                throw RuntimeException("params must be null")
            }
            request(HttpMethod.PUT_RAW)
        }
    }
    fun delete() {
        request(HttpMethod.DELETE)
    }
    fun upload() {
        request(HttpMethod.UPLOAD)
    }
    fun download() {
        DownloadHandler(URL, REQUEST, DOWNLOAD_DIR, EXTENSION, NAME,
            SUCCESS, FAILURE, ERROR)
            .handleDownload()
    }
    companion object {
        private val PARAMS = RestCreator.params
        lateinit var appContext:Context
        lateinit var baseUrl:String
         var getHeaders: WeakHashMap<String, String>? = null
        var timeout: Int? = null
        fun init(context:Context, url:String, hashMap: WeakHashMap<String, String>?, timeout: Int?) {
            appContext = context
            baseUrl = url
            getHeaders = hashMap
            this.timeout = timeout
        }
        fun buider():RapiBuilder {
            return RapiBuilder()
        }
    }
}