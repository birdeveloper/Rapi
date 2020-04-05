package com.birdeveloper.rapisample

import java.io.Serializable
/**
Created by @birdeveloper , see profile link: https://github.com/birdeveloper
Created date: 04.05.2020
Author: Görkem KARA - gorkemkara.com.tr
 */
data class Model(
    var userId: Any,
    var id: Any,
    var title: Any,
    var body:Any
): Serializable{
}

