package com.birdeveloper.rapi

import android.content.Context
import com.birdeveloper.rapi.callback.IError
import com.birdeveloper.rapi.callback.IFailure
import com.birdeveloper.rapi.callback.IRequest
import com.birdeveloper.rapi.callback.ISuccess
import java.io.File
import java.util.WeakHashMap
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
/**
Created by @birdeveloper , see profile link: https://github.com/birdeveloper
Created date: 04.05.2020
Author: Görkem KARA - gorkemkara.com.tr
 */
class RapiBuilder internal constructor() {
    private var mUrl: String? = null
    private var mIRequest: IRequest? = null
    private var mISuccess: ISuccess? = null
    private var mIFailure: IFailure? = null
    private var mIError: IError? = null
    private var mBody: RequestBody? = null
    private var mContext: Context? = null
    private var mFile: File? = null
    private var mDownloadDir: String? = null
    private var mExtension: String? = null
    private var mName: String? = null
    fun request(url:String):RapiBuilder {
        this.mUrl = url
        return this
    }
    fun params(params:Map<String, Any>):RapiBuilder {
        PARAMS.putAll(params)
        return this
    }
    fun params(key:String, value:Any):RapiBuilder {
        PARAMS.put(key, value)
        return this
    }
    fun file(file:File):RapiBuilder {
        this.mFile = file
        return this
    }
    fun file(file:String):RapiBuilder {
        this.mFile = File(file)
        return this
    }
    fun name(name:String):RapiBuilder {
        this.mName = name
        return this
    }
    fun dir(dir:String):RapiBuilder {
        this.mDownloadDir = dir
        return this
    }
    fun extension(extension:String):RapiBuilder {
        this.mExtension = extension
        return this
    }
    fun raw(raw:String):RapiBuilder {
        this.mBody = RequestBody.create("application/json;charset=UTF-8".toMediaTypeOrNull(), raw)
        return this
    }
    fun success(iSuccess:ISuccess):RapiBuilder {
        this.mISuccess = iSuccess
        return this
    }
    fun onRequest(iRequest:IRequest):RapiBuilder {
        this.mIRequest = iRequest
        return this
    }
    fun failure(iFailure:IFailure):RapiBuilder {
        this.mIFailure = iFailure
        return this
    }
    fun error(iError:IError):RapiBuilder {
        this.mIError = iError
        return this
    }
    private fun checkParams():Map<String, Any> {
        if (PARAMS == null)
        {
            return WeakHashMap<String, Any>()
        }
        return PARAMS
    }
    fun build():Rapi {
        return Rapi(mUrl, PARAMS,
            mDownloadDir, mExtension, mName,
            mIRequest, mISuccess, mIFailure,
            mIError, mBody, mFile, mContext)
    }
    companion object {
        private val PARAMS = RestCreator.params
    }
}