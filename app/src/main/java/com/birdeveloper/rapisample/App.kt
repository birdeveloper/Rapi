package com.birdeveloper.rapisample

import android.app.Application
import com.birdeveloper.rapi.Rapi
import java.util.*
/**
Created by @birdeveloper , see profile link: https://github.com/birdeveloper
Created date: 04.05.2020
Author: Görkem KARA - gorkemkara.com.tr
 */
class App : Application(){
    lateinit var headers: WeakHashMap<String, String>
    override fun onCreate() {
        super.onCreate()
        headers = WeakHashMap()
        headers.put("Authorization", "Bearer vF9dft4qmTFDfd27asWEgf")
        Rapi.init(this,Constants.BASE_URL, null, 120)
    }
}