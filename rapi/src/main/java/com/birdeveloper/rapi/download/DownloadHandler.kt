package com.birdeveloper.rapi.download
/**
Created by @birdeveloper , see profile link: https://github.com/birdeveloper
Created date: 04.05.2020
Author: Görkem KARA - gorkemkara.com.tr
 */
import android.os.AsyncTask
import com.birdeveloper.rapi.RestCreator
import com.birdeveloper.rapi.callback.IError
import com.birdeveloper.rapi.callback.IFailure
import com.birdeveloper.rapi.callback.IRequest
import com.birdeveloper.rapi.callback.ISuccess
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class DownloadHandler(
    private val URL: String?,
    request: IRequest?,
    downDir: String?,
    extension: String?,
    name: String?,
    success: ISuccess?,
    failure: IFailure?,
    error: IError?
) {
    private var REQUEST: IRequest? = null
    private val DOWNLOAD_DIR: String
    private val EXTENSION: String
    private val NAME: String
    private val SUCCESS: ISuccess
    private val FAILURE: IFailure?
    private val ERROR: IError?
    fun handleDownload() {
        if (REQUEST != null) {
            REQUEST!!.onRequestStart()
        }
        RestCreator.restService.download(URL!!, PARAMS)
            .enqueue(object : Callback<ResponseBody?> {
                override fun onResponse(
                    call: Call<ResponseBody?>,
                    response: Response<ResponseBody?>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        val task = SaveFileTask(REQUEST, SUCCESS)
                        task.executeOnExecutor(
                            AsyncTask.THREAD_POOL_EXECUTOR,
                            DOWNLOAD_DIR, EXTENSION, responseBody, NAME
                        )
                        if (task.isCancelled()) {
                            if (REQUEST != null) {
                                REQUEST!!.onRequestEnd()
                            }
                        }
                    } else {
                        if (ERROR != null) {
                            ERROR.onError(response.code(), response.message())
                        }
                    }
                    RestCreator.params.clear()
                }

                override fun onFailure(
                    call: Call<ResponseBody?>,
                    t: Throwable
                ) {
                    if (FAILURE != null) {
                        FAILURE.onFailure()
                        RestCreator.params.clear()
                    }
                }
            })
    }

    companion object {
        private val PARAMS: WeakHashMap<String, Any> =
            RestCreator.params
    }

    init {
        REQUEST = request
        DOWNLOAD_DIR = downDir!!
        EXTENSION = extension!!
        NAME = name!!
        SUCCESS = success!!
        FAILURE = failure
        ERROR = error
    }
}
