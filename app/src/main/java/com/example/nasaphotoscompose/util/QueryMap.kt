package com.example.nasaphotoscompose.util

import com.example.nasaphotoscompose.util.Key.API_KEY


fun map(page: Int) : Map<String, String>{
    val map = HashMap<String, String>()
    map["sol"] = "1000"
    map["page"] = page.toString()
    map["api_key"] = API_KEY
    return map
}

fun mapCamera(page: Int, camera: String) : Map<String, String>{
    val map = HashMap<String, String>()
    map["sol"] = "1000"
    map["camera"] = camera
    map["page"] = page.toString()
    map["api_key"] = API_KEY
    return map
}