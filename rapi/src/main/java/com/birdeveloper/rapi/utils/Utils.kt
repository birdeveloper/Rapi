package com.birdeveloper.rapi.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.internal.LinkedTreeMap
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import java.io.Serializable
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.util.*
/**
Created by @birdeveloper , see profile link: https://github.com/birdeveloper
Created date: 04.05.2020
Author: Görkem KARA - gorkemkara.com.tr
 */

/*
fun String.toObject(model: Serializable): Serializable{
    val jsonString: String = this
    val data = model
    val gson = Gson().fromJson(jsonString, data::class.java)
    return data
}
public infix fun <T> String.toObject(that: Class<T>): T{
    var mutableData = MutableLiveData<T>()
    mutableData.value = Gson().fromJson(
        this,
        object : TypeToken<T>() {}.type
    )
    return mutableData.value!!
}*/

fun <T> String.getList(
    clazz: Class<T>?
): T? {
    val typeOfT: Type =
        TypeToken.getParameterized(List::class.java, clazz).type
    return Gson().fromJson(this, typeOfT)
}
