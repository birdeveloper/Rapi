package com.birdeveloper.rapisample

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.birdeveloper.rapi.Rapi
import com.birdeveloper.rapi.callback.IError
import com.birdeveloper.rapi.callback.IFailure
import com.birdeveloper.rapi.callback.IRequest
import com.birdeveloper.rapi.callback.ISuccess
import com.birdeveloper.rapisample.Constants.Companion.URL_USERS
import java.io.File

/**
Created by @birdeveloper , see profile link: https://github.com/birdeveloper
Created date: 04.05.2020
Author: Görkem KARA - gorkemkara.com.tr
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rapiDelete()
    }
    fun rapiGet(){
        Log.d("rapiGet", "run")
        Rapi.buider()
            .request(URL_USERS)
            .params("page",2)
            .success(object : ISuccess {
                override fun onSuccess(response: String) {
                    Log.d("onSuccess", response)
                }
            })
            .failure(object :IFailure{
                override fun onFailure() {
                    Log.d("onFailure", "yes")
                }
            })
            .error(object : IError{
                override fun onError(code: Int, msg: String) {
                    Log.d("onError", "code: $code  -----  msg: $msg")
                }

            })
            .build()
            .get()
    }
    fun rapiPost(){
        Log.d("rapiPost", "run")
        Rapi.buider()
            .request(URL_USERS)
            .params("name","Görkem")
            .params("job","Android Developer")
            .success(object : ISuccess {
                override fun onSuccess(response: String) {
                    Log.d("onSuccess", response)
                }
            })
            .failure(object :IFailure{
                override fun onFailure() {
                    Log.d("onFailure", "yes")
                }
            })
            .error(object : IError{
                override fun onError(code: Int, msg: String) {
                    Log.d("onError", "code: $code  -----  msg: $msg")
                }

            })
            .build()
            .post()
    }
    fun rapiPostRaw(){
        Log.d("rapiPostRaw", "run")
        val jsonStr = "{\"name\":\"Görkem\",\"job\":\"Android Developer\"}"
        Rapi.buider()
            .request(URL_USERS)
            .raw(jsonStr)
            .success(object : ISuccess {
                override fun onSuccess(response: String) {
                    Log.d("onSuccess", response)
                }
            })
            .failure(object :IFailure{
                override fun onFailure() {
                    Log.d("onFailure", "yes")
                }
            })
            .error(object : IError{
                override fun onError(code: Int, msg: String) {
                    Log.d("onError", "code: $code  -----  msg: $msg")
                }

            })
            .build()
            .post()
    }
    fun rapiPut(){
        Log.d("rapiPut", "run")
        Rapi.buider()
            .request(Constants.URL_USER_ID)
            .params("name","Resul")
            .params("job","Software Developer")
            .success(object : ISuccess {
                override fun onSuccess(response: String) {
                    Log.d("onSuccess", response)
                }
            })
            .failure(object :IFailure{
                override fun onFailure() {
                    Log.d("onFailure", "yes")
                }
            })
            .error(object : IError{
                override fun onError(code: Int, msg: String) {
                    Log.d("onError", "code: $code  -----  msg: $msg")
                }

            })
            .build()
            .put()
    }
    fun rapiDelete(){
        Log.d("rapiDelete", "run")
        Rapi.buider()
            .request(Constants.URL_USER_ID)
            .success(object : ISuccess {
                override fun onSuccess(response: String) {
                    Log.d("onSuccess", response)
                }
            })
            .failure(object :IFailure{
                override fun onFailure() {
                    Log.d("onFailure", "yes")
                }
            })
            .error(object : IError{
                override fun onError(code: Int, msg: String) {
                    Log.d("onError", "code: $code  -----  msg: $msg")
                }

            })
            .build()
            .delete()
    }
    fun rapiDownload(){
        Log.d("rapiDownload", "run download directory: " + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath)
        if (isStoragePermissionGranted()) {
            Log.d("permission","Permission is granted");
            Rapi.buider()
                .request(Constants.URL_DOWNLOAD)
                .dir(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath)
                .extension("doc")
                .name("example.doc")
                .success(object : ISuccess {
                    override fun onSuccess(response: String) {
                        Log.d("onSuccess", response)
                    }
                })
                .failure(object :IFailure{
                    override fun onFailure() {
                        Log.d("onFailure", "yes")
                    }
                })
                .error(object : IError{
                    override fun onError(code: Int, msg: String) {
                        Log.d("onError", "code: $code  -----  msg: $msg")
                    }
                })
                .build()
                .download()
        }else{
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Log.v("Permission: ",permissions[0]+ "was "+grantResults[0]);
            rapiUpload()
            //resume tasks needing this permission
        }
    }
    fun isStoragePermissionGranted():Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if ((checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) === PackageManager.PERMISSION_GRANTED))
            {
                Log.d("PERMISSION", "Permission is granted")
                return true
            }
            else
            {
                Log.d("PERMISSION", "Permission is revoked")
                ActivityCompat.requestPermissions(this, arrayOf<String>(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
                return false
            }
        }
        else
        { //permission is automatically granted on sdk<23 upon installation
            Log.d("PERMISSION", "Permission is granted")
            return true
        }
    }
    fun rapiUpload(){
        Log.d("rapiUpload", "run upload file:"+ Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath+"image.jpeg")
        if (isStoragePermissionGranted()){
            Rapi.buider()
                .request(Constants.URL_UPLOAD)
                .file(File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath+"/example.jpeg"))
                .onRequest(object : IRequest{
                    override fun onRequestStart() {
                        Log.d("onRequestStart", "run")
                    }

                    override fun onRequestEnd() {
                        Log.d("onRequestEnd", "run")
                    }

                })
                .success(object : ISuccess {
                    override fun onSuccess(response: String) {
                        Log.d("onSuccess", response)
                    }
                })
                .failure(object :IFailure{
                    override fun onFailure() {
                        Log.d("onFailure", "yes")
                    }
                })
                .error(object : IError{
                    override fun onError(code: Int, msg: String) {
                        Log.d("onError", "code: $code  -----  msg: $msg")
                    }
                })
                .build()
                .upload()
        }else{
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
        }
    }
}
