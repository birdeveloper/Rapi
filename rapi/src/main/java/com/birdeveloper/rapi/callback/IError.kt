package com.birdeveloper.rapi.callback
/**
 Created by @birdeveloper , see profile link: https://github.com/birdeveloper
 Created date: 04.05.2020
 Author: Görkem KARA - gorkemkara.com.tr
 */
interface IError {
    fun onError(code: Int, msg: String)
}