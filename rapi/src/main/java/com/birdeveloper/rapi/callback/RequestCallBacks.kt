package com.birdeveloper.rapi.callback
/**
Created by @birdeveloper , see profile link: https://github.com/birdeveloper
Created date: 04.05.2020
Author: Görkem KARA - gorkemkara.com.tr
 */
import android.os.Handler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RequestCallBacks(
    private val REQUEST: IRequest?, private val SUCCESS: ISuccess?, private val FAILURE: IFailure?
    , private val ERROR: IError?
) :
    Callback<String?> {
    override fun onResponse(
        call: Call<String?>,
        response: Response<String?>
    ) {
        if (response.isSuccessful) {
            if (call.isExecuted) {
                SUCCESS?.onSuccess(if (response.body() != null) response.body()!! else "The response from the server is empty! Please check your api. @Rapi wishes you success :)")
            }
        } else {
            ERROR?.onError(response.code(), response.message())
        }
    }

    override fun onFailure(
        call: Call<String?>,
        t: Throwable
    ) {
        FAILURE?.onFailure()
        REQUEST?.onRequestEnd()

    }


    companion object {
        private val HANDLER = Handler()
    }

}
