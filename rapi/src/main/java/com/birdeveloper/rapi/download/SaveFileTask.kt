package com.birdeveloper.rapi.download
/**
Created by @birdeveloper , see profile link: https://github.com/birdeveloper
Created date: 04.05.2020
Author: Görkem KARA - gorkemkara.com.tr
 */
import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import com.birdeveloper.rapi.Rapi
import com.birdeveloper.rapi.callback.IRequest
import com.birdeveloper.rapi.callback.ISuccess
import com.birdeveloper.rapi.utils.FileUtil
import java.io.File
import okhttp3.ResponseBody
internal class SaveFileTask(REQUEST: IRequest?, SUCCESS: ISuccess):AsyncTask<Any, Void, File>() {
    private var REQUEST:IRequest? = null
    private val SUCCESS:ISuccess
    init{
        this.REQUEST = REQUEST
        this.SUCCESS = SUCCESS
    }
     override fun doInBackground(vararg params:Any):File {
        var downloadDir = params[0] as String
        var extension = params[1] as String
        val body = params[2] as ResponseBody
        val name = params[3] as String
        val `is` = body.byteStream()
        if (downloadDir == null || downloadDir == "")
        {
            downloadDir = "down_loads"
        }
        if (extension == null || extension == "")
        {
            extension = ""
        }
        if (name == null)
        {
            return FileUtil.writeToDisk(`is`, downloadDir, extension.toUpperCase(), extension)
        }
        else
        {
            return FileUtil.writeToDisk(`is`, downloadDir, name)
        }
    }
     override fun onPostExecute(file:File) {
        super.onPostExecute(file)
        if (SUCCESS != null)
        {
            SUCCESS.onSuccess(file.getPath())
        }
        if (REQUEST != null)
        {
            REQUEST!!.onRequestEnd()
        }
        autoInstallApk(file)
    }
    private fun autoInstallApk(file:File) {
        if (FileUtil.getExtension(file.getPath()).equals("apk"))
        {
            val install = Intent()
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            install.setAction(Intent.ACTION_VIEW)
            install.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive")
            Rapi.appContext.startActivity(install)
        }
    }
}